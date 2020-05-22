import autobind from 'autobind-decorator';
import jwtDecode from 'jwt-decode';
import { action, observable, reaction } from 'mobx';
import AuthService from '../service/AuthService';

export interface IAuth {
  email: string;
  id: number;
  provider: string;
}

export const initialAuth: IAuth = {
  email: '',
  id: -1,
  provider: '',
};

export type Provider = 'kakao' | 'google' | 'naver';

@autobind
class AuthStore {
  @observable token: string = '';
  @observable refreshToken: string = '';
  @observable auth: IAuth | undefined;
  @observable email = '';
  @observable provider: string = '';

  private authService = new AuthService();

  constructor(initialData = initialAuth, root: any) {
    if (this.token) {
      this.auth = jwtDecode(this.token) as IAuth;
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
  setProvider(provider : Provider) {
    this.provider = provider;
  }

  @action
  getTest() {
    console.log("call store");
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
    this.auth = jwtDecode(token) as IAuth;
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
