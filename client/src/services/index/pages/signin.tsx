import * as React from 'react';
import KakaoLogin from 'react-kakao-login';
import { useGetLoginMutation } from '~/generated/graphql';
import styled from '~/styled';
import GoogleIcon from '../assets/img/signin-google.png?url';
import KakaoIcon from '../assets/img/signin-kakao.png?url';
import {
  NEXT_APP_KAKAO_CLIENT_KEY,
} from '../helpers/config';
import useStores from '../helpers/useStores';
import { IAuthResponseDto } from '../store/AuthStore';

interface ISignInProps {}

export default (props: ISignInProps) => {
  const store = useStores();

  // const mutate = useGetLoginMutation;
  const mutate = useGetLoginMutation();

  const success = (res: any) => {
    mutate({
      variables: {
        input: {
          provider: 'Kakao',
          token: res.response.access_token,
        },
      },
    })
    .then((res: {data : IAuthResponseDto}) => {
      store.authStore.setToken(res.data.loginUser.token);
      store.authStore.setProvider('kakao');
    })
    .catch((err) => {
      console.log(err);
    })
  };
  const failure = () => {
    alert('실패');
  };

  const getNaverAuth = () => {};
  return (
    <Wrapper>
      <WrapperLine>
        <Line>소개 Thing</Line>
      </WrapperLine>
      <StyledKakaoLogin
        jsKey={NEXT_APP_KAKAO_CLIENT_KEY!}
        onSuccess={success}
        onFailure={failure}
      >
        <LoginText>카카오로 시작하기</LoginText>
      </StyledKakaoLogin>
      <LoginButton type={'google'}>
        <LoginText>구글계정으로 시작하기</LoginText>
      </LoginButton>
      <LoginButton type={'naver'}>
        <LoginText>네이버로 시작하기</LoginText>
      </LoginButton>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  padding: 40px;
  height: 100%;
`;

const WrapperLine = styled.div`
  text-align: center;
  display: flex;
  align-items: center;
  text-align: center;
  height: 50%;
  width:100%;
`;

const WrapperLoginImageText = styled.div`
  width: 80%;
  display: flex;
  align-items: center;
  margin: auto;
`;

const Line = styled.p`
  margin: 22px 0.5rem 0rem 0;
  width: 100%;
  font-size: 36px;
  font-weight: bold;
  font-family: "Jua";
  color: ${(props) => props.theme.pointFontColor};
`;

const LoginText = styled.div`
  font-family: "Noto sans";
  font-weight: bold;
  margin: auto;
  text-align: center;
  font-size: 16px;
`;

const StyledKakaoLogin = styled(KakaoLogin)`
  display: flex;
  height: 49px;
  width: 100%;
  margin-top: 13px;
  background-color: ${(props) => props.theme.button.login.kakao.bg};
  border-radius: 25px;
  border: 0ch;
`;

const LoginButton = styled.div<{ type: 'google' | 'naver' }>`
  height: 49px;
  width: 100%;
  display: flex;
  border-radius: 25px;
  background-color: ${(props) => props.theme.button.login[props.type].bg};
  border: ${(props) =>
    props.theme.button.login[props.type].border !== 'none'
      ? '1px solid ' + props.theme.button.login[props.type].border
      : 'none'};
  margin-top: 13px;
  margin-bottom: 13px;
`;
