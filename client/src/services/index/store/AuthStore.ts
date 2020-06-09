import autobind from 'autobind-decorator';
import { Request, Response } from 'express-serve-static-core';
import jwtDecode from 'jwt-decode';
import { action, autorun, observable, reaction, toJS } from 'mobx';
import { checkTokenIsExpired } from '../helpers';

export interface IAuth {
  sub: string;
  userId: number;
  userName: string;
}

export interface IAuthResponseDto {
  loginUser: {
    token: string;
  };
}

export const initialAuth = {
  token: '',
  auth: {},
};

export interface IToken {
  token: string;
}

export type Provider = 'kakao' | 'google' | 'naver';

@autobind
class AuthStore {
  @observable token: string = '';
  @observable refreshToken: string = '';
  @observable auth: IAuth;
  @observable email = '';
  @observable provider: string = '';
  @observable imgurl: string = '';

  constructor(root: any, initialData?: AuthStore) {
    if (initialData) {
      this.auth = initialData!.auth;
      this.token = initialData!.token;
    } else {
      this.auth = {
        sub: '',
        userId: 0,
        userName: '',
      };
      this.token = '';
      this.imgurl = '';
    }
  }

  isLoggedIn() {
    return this.token != null;
  }

  @action
  async refreshTokens(_tokens: IToken): Promise<any> {
    // will be type change
    return {
      accessToken: '',
      refreshToken: '',
    };
  }
  // @action
  // async login() {
  //   const body: LoginSignupRequestDto = {
  //     email: this.email,
  //     password: this.password
  //   };
  //   const response = await this.authService.login(body);
  //   this.setToken(response.data.data.token);
  // }

  // async signUp(auth: LoginSignupRequestDto) {
  //   return await this.authService.signUp(auth);
  // }

  @action
  setProvider(provider: Provider) {
    this.provider = provider;
  }

  @action
  resetPasswordAndEmail() {
    this.email = '';
  }

  @action
  setPassword(pw: string) {}

  @action
  setEmail(email: string) {
    this.email = email;
  }

  @action
  setToken(token: string) {
    this.token = token;
    this.setAuth();
  }
  @action
  setAuth() {
    if (this.token) {
      this.auth = jwtDecode(this.token) as IAuth;
    }
  }

  @action
  getAuth() {
    if (this.token) {
      return this.auth;
    }
  }

  @action
  setRefreshToken(token: string) {
    this.refreshToken = token;
  }

  @action
  signOut() {
    window.sessionStorage.removeItem('jwt');
    this.token = '';
    this.auth = {
      sub: 'logout',
      userId: -1,
      userName: '',
    };
  }
  async nextServerInit(req: Request, res: Response) {
    try {
      if (!req || !res) {
        console.error('req, res 값 없음');
        throw new Error();
      }
      if (!req.headers.cookie) {
        console.error('쿠키에 토큰 없음');
        throw new Error();
      }

      const cookieString: string = req.headers.cookie as string;

      const tokens: IToken = {
        token: '',
      };

      for (const item of cookieString.split('; ')) {
        const cookie = item.split('=');
        if (cookie[0] === 'token') {
          tokens.token = cookie[1];
        }
      }

      const isRefreshTokenExpired = checkTokenIsExpired(tokens.token);
      const isAccessTokenExpired = checkTokenIsExpired(tokens.token);

      if (isRefreshTokenExpired) {
        throw new Error();
      }

      if (isAccessTokenExpired) {
        const refreshedTokens = await this.refreshTokens(tokens);

        tokens.token = refreshedTokens.token;
      }
      this.setToken(tokens.token);
    } catch (error) {
      // console.error(error);
      console.error('error in nextServerInit');
    }
  }

  @action
  setUserImage(data: string) {
    this.imgurl = data;
  }
}

export default AuthStore;
