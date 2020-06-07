import * as React from 'react';
import { FaMoneyBillWave, FaRegHeart, FaShoppingBag } from 'react-icons/fa';
import { IoIosPin, IoIosSettings } from 'react-icons/io';
import styled from '~/styled';
import Camera from '../../assets/img/circle-camera.png?url';
import NoAvatar from '../../assets/img/no-avatar.png?url';
import Categoryheader from '../../components/CategoryHeader';
import CircleImageView from '../../components/CircleImageView';
import Nav from '../../components/Nav';
import { MdGpsFixed } from 'react-icons/md';

export interface IUserProps {}

export default (props: IUserProps) => {
  return (
    <Wrapper>
      <Categoryheader type={'chat'} text={'내 정보'} />
      <Container>
        <WrapperRow>
          <WrapperImg>
            <CircleImageView src={NoAvatar} size={3} />
          </WrapperImg>
          <WrapperUserInfo>
            <FlexContainer>
              <BoldText>유일한 </BoldText>
              <SmallText>lv 1</SmallText>
            </FlexContainer>
            <SmallText>경기도 수원시 영통구</SmallText>
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
      <Nav />
    </Wrapper>
  );
};

const Wrapper = styled.div`
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
