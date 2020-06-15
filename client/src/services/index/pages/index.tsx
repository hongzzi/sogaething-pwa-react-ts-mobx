import Link from 'next/link';
import { useRouter } from 'next/router';
import * as React from 'react';
import KakaoLogin from 'react-kakao-login';
import { useGetLoginMutation } from '~/generated/graphql';
import styled from '~/styled';
import Logo from '../assets/img/main-logo.png?url';
import ImageView from '../components/ImageView';
import { KEYS } from '../constants';
import { setCookie } from '../helpers';
import {
  NEXT_APP_KAKAO_CLIENT_KEY,
} from '../helpers/config';
import useStores from '../helpers/useStores';
import { IAuthResponseDto } from '../store/AuthStore';

interface ISignInProps {}

const isServer = typeof window === 'undefined';

export default (props: ISignInProps) => {
  const store = useStores();
  // const store = useStore();
  // console.log(store);
  const mutate = useGetLoginMutation();
  const router = useRouter();
  const success = (res: any) => {
    mutate({
      variables: {
        input: {
          provider: 'Kakao',
          token: res.response.access_token,
        },
      },
    })
    .then((res: {data: IAuthResponseDto}) => {
      store.authStore.setToken(res.data.loginUser.token);
      setCookie('token', res.data.loginUser.token);
      router.push('/main');
    })
    .catch((err) => {
      router.push('/');
      console.log(err);
    })
  };
  const failure = () => {
    alert('실패');
  };

  const getNaverAuth = () => {};
  return (
    <Wrapper>
      <WrapperSigninContainer>
      <WrapperLogo>
        <DivLogo />
      </WrapperLogo>
      <StyledKakaoLogin
        jsKey={KEYS.KAKAO}
        onSuccess={success}
        onFailure={failure}
      >
        <LoginText>카카오로 시작하기</LoginText>
      </StyledKakaoLogin>
      <LoginButton type={'facebook'}>
        <LoginText>페이스북으로 시작하기</LoginText>
      </LoginButton>
      </WrapperSigninContainer>
      <Background />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  border-bottom-left-radius: 100% 25%;
  border-bottom-right-radius: 100% 25%;
  background: white;
  width: 100%;
  height: 90%;
  overflow: hidden;
`;

const WrapperLogo = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 20vh;
  margin-bottom: 5vh;
  width:100%;
`;

const DivLogo = styled.div`
  background-image: url(${Logo});
  background-repeat: no-repeat;
  align-items: center;
  text-align: center;
  min-width: 35%;
  max-width: 60%;
  height: 25vh;
  background-size: contain;
`;

const WrapperLoginImageText = styled.div`
  width: 80%;
  display: flex;
  align-items: center;
  margin: auto;
`;

const Line = styled.p<{size: number}>`
  /* margin: 22px 0.5rem 0rem 0; */
  margin: 0;
  font-size: ${(props) => props.size + 'px'};
  font-weight: bold;
  font-family: "TmonMonsori";
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
  height: 52px;
  width: 100%;
  background-color: ${(props) => props.theme.button.login.kakao.bg};
  border-radius: 25px;
  border: 3px solid ${(props) => props.theme.button.login.kakao.border};
`;

const LoginButton = styled.div<{ type: 'facebook' | 'naver' }>`
  height: 52px;
  width: 100%;
  display: flex;
  border-radius: 25px;
  background-color: ${(props) => props.theme.button.login[props.type].bg};
  border: ${(props) =>
    props.theme.button.login[props.type].border !== 'none'
      ? '3px solid ' + props.theme.button.login[props.type].border
      : 'none'};
  margin-top: 13px;
  margin-bottom: 13px;
`;

const WrapperSigninContainer = styled.div`
  height: 90%;
  width: 100%;
  padding: 40px;
`;

const Background = styled.div`
  position: absolute;
  z-index: -1;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 30%;
  background-image: linear-gradient(to bottom, #259be5, #6459db);
`;
