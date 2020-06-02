import * as React from 'react';
import { useGetUserInfoQuery } from '~/generated/graphql';
import styled from '~/styled';
import Pin from '../../assets/img/pin-fill.png?url';
import CircleImageView from '../CircleImageView';
import { TextLoader } from '../LoaderPlaceholder';
import CirclePlaceHolder from '../LoaderPlaceholder/Circle';

interface IQueryData {
  findUserInfo: IFindUserInfo | null;
}

export interface IFindUserInfo {
  address?: string | null;
  name: string;
  numOfPosts: number;
  trust: number;
  imgurl: string;
}

export default () => {
  const { data, loading, error } = useGetUserInfoQuery();
  const { findUserInfo } = data as IQueryData;
  const handleClickMatch = () => {
    console.log(findUserInfo);
    console.log(error);
  };
  return (
    <Wrapper>
      <WrapperFlex>
        {loading && <CirclePlaceHolder size={4} />}
        {!loading && (
          <CircleImageView
            size={4}
            src={
              'https://pngimage.net/wp-content/uploads/2018/05/default-user-profile-image-png-6.png'
            }
            radius={35}
          />
        )}
        <WrapperUserInfo>
          <TextUserInfo>
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
        <MainButton>
          <div>진행중인 매칭</div> <InnerLine>3</InnerLine>
        </MainButton>
        <MainButton onClick={handleClickMatch}>
          <div>판매글 목록</div> <InnerLine>0</InnerLine>
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
  color: ${props => props.theme.mainFontColor};
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
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.5);
  background-image: linear-gradient(to right, #259be5, #6459db);
  margin-bottom: 17px;
  width: 45%;
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
