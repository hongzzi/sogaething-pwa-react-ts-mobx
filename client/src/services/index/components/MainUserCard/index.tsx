import * as React from 'react';
import { useGetUserInfoQuery } from '~/generated/graphql';
import styled from '~/styled';
import CircleImageView from '../CircleImageView';
import { TextLoader } from '../LoaderPlaceholder';
import CirclePlaceHolder from '../LoaderPlaceholder/Circle';

interface IQueryData {
  findUserInfo: IFindUserInfo | null;
}

export interface IFindUserInfo {
  address ?: string | null;
  name: string;
  numOfPosts: number;
  trust: number;
}

export default () => {
  const { data, loading, error } = useGetUserInfoQuery();
  const {findUserInfo} = data as IQueryData;
  const handleClickMatch = () => {
    console.log(findUserInfo);
  }
  return (
    <Wrapper>
      <WrapperFlex>
        {loading && <CirclePlaceHolder size={3}/>}
        {!loading && <CircleImageView
          size={3}
          src={
            'https://pngimage.net/wp-content/uploads/2018/05/default-user-profile-image-png-6.png'
          }
        />}
        <WrapperUserInfo>
            <TextUserInfo>
                {loading && <TextLoader size={{width: 50, height: 18}} />}
                {!loading && findUserInfo!.name}
            </TextUserInfo>
            <TextuserAddr>
                {loading && <TextLoader size={{width: 80, height: 12}} />}
                {!loading && findUserInfo!.address && findUserInfo!.address}
                {!loading && !findUserInfo!.address && '주소를 등록해주세요'}
            </TextuserAddr>
        </WrapperUserInfo>
      </WrapperFlex>
      <WrapperMainButton>
            <MainButton>
                <p>판매 현황</p>
            </MainButton>
            <MainButton onClick={handleClickMatch}>
                <p>구매 현황</p>
            </MainButton>
        </WrapperMainButton>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  margin-top: 30px;
  height: 210px;
  width: 100%;
  border-radius: 15px;
  background-color: ${(props) => props.theme.mainUserCardBGColor};
  padding: 20px;
`;

const WrapperFlex = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const WrapperUserInfo = styled.div`
    margin: 15px;
    flex-grow: 8;
`;

const TextUserInfo = styled.div`
  font-size: 18px;
  font-weight: bold;
  color: white;
`;

const TextuserAddr = styled.div`
    color: ${(props) => props.theme.subFontColor};
    font-size: 12px;
`;

const WrapperMainButton = styled.div`
    display: flex;
    text-align: center;
    justify-content: space-between;
`;

const MainButton = styled.div`
    display: flex;
    text-align: center;
    align-items: center;
    margin-top: 55px;
    margin-bottom: 17px;
    width: 45%;
    height: 48px;
    border-radius: 7px;
    background-color: #ffffff;
    font-size: 16px;
    &>p{
      margin: auto;
    }
`;
