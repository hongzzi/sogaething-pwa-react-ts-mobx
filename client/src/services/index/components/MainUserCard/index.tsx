import * as React from 'react';
import styled from '~/styled';
import CircleImageView from '../CircleImageView';

export default () => {
  return (
    <Wrapper>
      <WrapperFlex>
        <CircleImageView
          size={3}
          src={
            'https://pngimage.net/wp-content/uploads/2018/05/default-user-profile-image-png-6.png'
          }
        />
        <WrapperUserInfo>
            <TextUserInfo>
                박지홍
            </TextUserInfo>
            <TextuserAddr>
                경기도 화성시 봉담읍
            </TextuserAddr>
        </WrapperUserInfo>
      </WrapperFlex>
      <WrapperMainButton>
            <MainButton>
                <p>판매 현황</p>
            </MainButton>
            <MainButton>
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
    justify-content: space-between;
`;

const MainButton = styled.div`
    margin-top: 55px;
    margin-bottom: 17px;
    width: 45%;
    height: 48px;
    border-radius: 7px;
    background-color: #ffffff;
    text-align: center;
    font-size: 16px;
`;
