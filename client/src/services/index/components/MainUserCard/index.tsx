import { useObserver } from 'mobx-react';
import { useRouter } from 'next/router';
import * as React from 'react';
import { useGetUserInfoQuery } from '~/generated/graphql';
import styled from '~/styled';
import NoAvatar from '../../assets/img/no-avatar.png?url';
import Pin from '../../assets/img/pin-fill.png?url';
import useStores from '../../helpers/useStores';
import CircleImageView from '../CircleImageView';
import { TextLoader } from '../LoaderPlaceholder';
import CirclePlaceHolder from '../LoaderPlaceholder/Circle';

interface IQueryData {
  findUserInfo: IFindUserInfo | null;
}

export interface IFindUserInfo {
  address?: string;
  name: string;
  numOfPosts: number;
  trust: number;
  imgurl: string;
}

function useAuthData() {
  const { authStore } = useStores();
  return useObserver(() => ({
    // useObserver를 사용해서 리턴하는 값의 업데이트를 계속 반영한다
    imgurl: authStore.imgurl,
  }));
}

export default () => {
  const router = useRouter();
  const store = useStores();
  const { data, loading, error } = useGetUserInfoQuery();
  const {imgurl} = useAuthData();

  const handleClickMatch = () => {
    if (store.authStore.auth) {
      router.push(`/match/${store.authStore.auth.userId}`);
    } else {
      alert('요청이 실패했습니다. 잠시 후 다시 시도해주세요.');
    }
  };
  const handleClickList = () => {
    if (store.authStore.auth) {
      router.push(`/list/seller/${store.authStore.auth.userId}`);
    } else {
      alert('요청이 실패했습니다. 잠시 후 다시 시도해주세요.');
    }
  };

  const { findUserInfo } = data as IQueryData;

  if (loading || error) {
    return (
      <Wrapper>
        <WrapperFlex>
          <CirclePlaceHolder size={4} />
          <WrapperUserInfo>
            <TextUserInfo>
              <TextLoader size={{ width: 50, height: 18 }} />
            </TextUserInfo>
            <TextuserAddr>
              <SmallIcon src={Pin} />
              <TextLoader size={{ width: 80, height: 12 }} />
            </TextuserAddr>
          </WrapperUserInfo>
        </WrapperFlex>
        <WrapperMainButton>
          <MainButton onClick={handleClickMatch}>
            <div>진행중인 매칭</div>
            {/* <InnerLine>3</InnerLine> */}
          </MainButton>
          <MainButton onClick={handleClickList}>
            <div>판매중인 물품</div>
            {/* <InnerLine>0</InnerLine> */}
          </MainButton>
        </WrapperMainButton>
      </Wrapper>
    );
  }

  const handleClickTest = () => {
    console.log(data);
    console.log(store.authStore.auth);
  };
  return (
    <Wrapper>
      <WrapperFlex>
        {loading && <CirclePlaceHolder size={4} />}
        {!loading && findUserInfo && (
          <CircleImageView size={4} src={store.authStore.imgurl! ? store.authStore.imgurl : findUserInfo.imgurl} radius={35} />
        )}
        <WrapperUserInfo>
          <TextUserInfo onClick={handleClickTest}>
            {loading && <TextLoader size={{ width: 50, height: 18 }} />}
            {!loading && findUserInfo && findUserInfo!.name}
          </TextUserInfo>
          <TextuserAddr>
            <SmallIcon src={Pin} />
            {loading && <TextLoader size={{ width: 80, height: 12 }} />}
            {!loading && findUserInfo!.address && findUserInfo!.address}
            {!loading && !findUserInfo!.address && '주소를 등록해주세요'}
          </TextuserAddr>
        </WrapperUserInfo>
      </WrapperFlex>
      <WrapperMainButton>
        <MainButton onClick={handleClickMatch}>
          <div>진행중인 매칭</div>
          {/* <InnerLine>3</InnerLine> */}
        </MainButton>
        <MainButton onClick={handleClickList}>
          <div>판매중인 물품</div>
          {/* <InnerLine>0</InnerLine> */}
        </MainButton>
      </WrapperMainButton>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  margin-top: 30px;
  height: 242px;
  width: 100%;
  border-radius: 15px;
  background-color: ${(props) => props.theme.mainUserCardBGColor};
  padding: 20px;
  box-shadow: 0 2px 11px 0 rgba(191, 191, 191, 0.5);
`;

const WrapperFlex = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  justify-content: space-between;
`;

const WrapperUserInfo = styled.div`
  margin: 15px;
  flex-grow: 8;
`;

const TextUserInfo = styled.div`
  font-size: 18px;
  font-weight: bold;
  color: ${(props) => props.theme.mainFontColor};
`;

const TextuserAddr = styled.div`
  display: flex;
  align-items: center;
  color: #397daa;
  font-weight: 500;
  font-size: 12px;
`;

const WrapperMainButton = styled.div`
  display: flex;
  text-align: center;
  padding: 0 0.3rem;
  justify-content: space-between;
`;

const MainButton = styled.div`
  display: flex;
  padding: 15px;
  width: 100%;
  font-weight: 500;
  text-align: center;
  align-items: center;
  justify-content: space-around;
  color: white;
  font-family: GmarketSansTTF;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: 0 2px 4px 0 rgba(30, 30, 30, 0.5);
  background-image: linear-gradient(to right, #259be5, #6459db);
  margin-bottom: 17px;
  width: 46%;
  height: 48px;
  border-radius: 7px;
  background-color: #ffffff;
  font-size: 16px;
  & > * {
    font-family: GmarketSansTTF;
  }
`;

const InnerLine = styled.div`
  display: inline;
  font-family: GmarketSansTTF;
  font-size: 20px;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  color: #f36f5f;
`;

const SmallIcon = styled.img`
  width: 14px;
  height: 14px;
  margin: 0;
`;
