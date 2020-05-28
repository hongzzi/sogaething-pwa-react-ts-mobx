import autobind from 'autobind-decorator';
import jwtDecode from 'jwt-decode';
import { action, observable, reaction, toJS } from 'mobx';

export interface IAuth {
  sub: number;
}

export interface IAuthResponseDto {
  loginUser: {
    token: string,
  }
}

export const initialAuth = {
  token: '',
};

export type Provider = 'kakao' | 'google' | 'naver';

@autobind
class AuthStore {
  @observable token: string = '';
  @observable refreshToken: string = '';
  @observable auth: IAuth | undefined;
  @observable email = '';
  @observable provider: string = '';

  constructor(initialData = initialAuth, root: any) {
    if (this.token) {
      this.auth = jwtDecode(this.token) as IAuth;
    }

    if(initialData.token !== ''){
      this.setToken(initialData.token);
    }

    reaction(
      () => this.token,
      (token) => {
        if (token != null) {
          window.sessionStorage.setItem('jwt', token);
        }
      },
    );
  }

  isLoggedIn() {
    return this.token != null;
  }

  async refreshTokens(_tokens: {
    accessToken: string;
    refreshTokens: string;
  }): Promise<any> {
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
  getTest() {
    console.log('call store');
  }

  @action
  resetPasswordAndEmail() {
    this.email = '';
  }

  @action
  setPassword(pw: string) {
  }

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
    this.auth = undefined;
  }
}

export default AuthStore;
