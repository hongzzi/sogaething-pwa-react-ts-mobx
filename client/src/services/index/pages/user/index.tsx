import { useObserver } from 'mobx-react';
import * as React from 'react';
import { FaMoneyBillWave, FaRegHeart, FaShoppingBag } from 'react-icons/fa';
import { IoIosPin, IoIosSettings } from 'react-icons/io';
import { MdGpsFixed } from 'react-icons/md';
import {
  useGetUserInfoQuery,
  usePutUpdateImgMutation,
} from '~/generated/graphql';
import styled from '~/styled';
import Camera from '../../assets/img/circle-camera.png?url';
import NoAvatar from '../../assets/img/no-avatar.png?url';
import Categoryheader from '../../components/CategoryHeader';
import CircleImageView from '../../components/CircleImageView';
import Loader from '../../components/Loader';
import Nav from '../../components/Nav';
import useStores from '../../helpers/useStores';

export interface IUserProps {}

function useAuthData() {
  const { authStore } = useStores();
  return useObserver(() => ({
    // useObserver를 사용해서 리턴하는 값의 업데이트를 계속 반영한다
    imgurl: authStore.imgurl,
  }));
}

export default (props: IUserProps) => {
  const userInfo = useGetUserInfoQuery();
  const mutationUpdateImg = usePutUpdateImgMutation();
  const [userImg, setUserImg] = React.useState(NoAvatar);
  const { authStore } = useStores();
  const { imgurl } = useAuthData();
  const [loading, setLoading] = React.useState(false);

  React.useEffect(() => {
    if (userInfo.loading || !userInfo.data) {
      return;
    }
    if (userInfo.data) {
      setUserImg(imgurl);
      return;
    }
  }, [userImg]);

  if (userInfo.loading || !userInfo.data) {
    return (
      <Wrapper>
        <Loader />
      </Wrapper>
    );
  }

  const fileInput = React.useRef<HTMLInputElement>(null);

  const handleClick = (e: React.MouseEvent) => {
    e.stopPropagation();
    fileInput.current!.click();
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      const files = Array.from(e.target.files);
      if (files) {
        const reader = new FileReader();
        setLoading(true);
        reader.onloadend = () => {
          if (reader.result) {
            const targetImg = reader.result.toString();
            mutationUpdateImg({
              variables: {
                input: {
                  imageUrl: targetImg,
                },
              },
            })
              .then((res) => {
                setUserImg(res.data.updateImg.imageUrl);
                authStore.setUserImage(res.data.updateImg.imageUrl);
                setLoading(false);
              })
              .catch((e) => {
                console.error(e);
              })
              .finally(() => {
                setLoading(false);
              });
          }
        };
        reader.readAsDataURL(files[0]);
      }
    }
  };

  const { data } = userInfo;
  return (
    <Wrapper>
      {loading && <Loader />}
      {!loading && <Categoryheader type={'chat'} text={'내 정보'} />}
      {!loading && (
        <Container>
          <FileInput
            type='file'
            ref={fileInput}
            accept='image/*'
            onChange={handleFileChange}
          />
          <WrapperRow>
            <WrapperImg onClick={handleClick}>
              <div onClick={handleClick}>
                <CircleImageView
                  src={
                    authStore.imgurl
                      ? authStore.imgurl
                      : data.findUserInfo!.imgurl!
                  }
                  size={3}
                />
              </div>
            </WrapperImg>
            <WrapperUserInfo>
              <FlexContainer>
                <BoldText> {data.findUserInfo!.name} </BoldText>
                <SmallText>lv 1</SmallText>
              </FlexContainer>
              <SmallText>
                {data.findUserInfo!.address
                  ? data.findUserInfo!.address
                  : '주소를 등록하세요.'}
              </SmallText>
            </WrapperUserInfo>
          </WrapperRow>
          <WrapperRow>
            <CenterFelxContainer>
              <div>
                <WrapperCircle>
                  <FaMoneyBillWave size={30} color={'#3466ac'} /> <br />
                </WrapperCircle>
                판매내역
              </div>
              <div>
                <WrapperCircle>
                  <FaShoppingBag size={30} color={'#3466ac'} /> <br />
                </WrapperCircle>
                구매내역
              </div>
              <div>
                <WrapperCircle>
                  <FaRegHeart size={30} color={'#3466ac'} /> <br />
                </WrapperCircle>
                찜목록
              </div>
            </CenterFelxContainer>
          </WrapperRow>
          <WrapperRow>
            <ListContainer>
              <CommonFelxContainer>
                <IoIosPin size={25} /> <Text>내 동네 설정</Text>
              </CommonFelxContainer>
              <CommonFelxContainer>
                <MdGpsFixed size={25} /> <Text>내 동네 인증</Text>
              </CommonFelxContainer>
            </ListContainer>
          </WrapperRow>
          <WrapperRow>
            <ListContainer>
              <CommonFelxContainer>
                <IoIosSettings size={25} /> <Text>앱 설정</Text>
              </CommonFelxContainer>
            </ListContainer>
          </WrapperRow>
        </Container>
      )}

      <Nav />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  height: 100%;
`;

const Container = styled.div`
  padding: 56px 16px 16px 16px;
`;

const WrapperRow = styled.div`
  padding-top: 2vh;
  padding-bottom: 2vh;
  display: flex;
  align-items: center;
  border-bottom: solid 1px ${(props) => props.theme.bolderColor};
`;

const WrapperUserInfo = styled.div`
  padding: 5px;
`;

const BoldText = styled.div`
  font-size: 16px;
  font-weight: bold;
  padding-right: 5px;
`;

const Text = styled.div`
  font-size: 16px;
  padding-left: 10px;
`;

const SmallText = styled.div`
  font-size: 12px;
  color: ${(props) => props.theme.subFontColor};
`;

const WrapperImg = styled.div`
  position: relative;
  &::after {
    content: "";
    position: absolute;
    top: 0;
    right: 0;
    width: 20px;
    height: 20px;
    background-image: url(${Camera});
    background-size: cover;
  }
`;

const FlexContainer = styled.div`
  display: flex;
  align-items: baseline;
`;

const ListContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const CommonFelxContainer = styled.div`
  display: flex;
  align-items: center;
  padding-top: 1vh;
  padding-bottom: 1vh;
`;

const WrapperCircle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: white;
  border: 1px solid ${(props) => props.theme.bolderColor};
  height: 60px;
  width: 60px;
  border-radius: 50%;
`;

const CenterFelxContainer = styled.div`
  text-align: center;
  margin: auto;
  width: 80%;
  display: flex;
  align-items: center;
  justify-content: space-around;
`;

const FileInput = styled.input`
  /* width: 100px;
  height: 20px; */
  display: none;
`;
