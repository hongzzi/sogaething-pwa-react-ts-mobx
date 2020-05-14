import axios from 'axios';
import { ApiResponse } from './types';

export interface ILoginResponseDto {
  token: string;
  id: number;
}

export interface ILoginSignupRequestDto {
  email: string;
  password: string;
}

export interface IAuthResponseDto {
  id: string;
  email: string;
  password: string;
}

const API_HOST = process.env.API_HOST || 'http://localhost:5000/api';

class AuthService {
  async login(body: ILoginSignupRequestDto): Promise<ApiResponse<ILoginResponseDto>> {
    return axios.post(`${API_HOST}/auth/login`, body);
  }

  async signUp(body: ILoginSignupRequestDto): Promise<ApiResponse<IAuthResponseDto>> {
    return axios.post(`${API_HOST}/auth/signup`, body);
  }

  test() {
    return 'this is test';
  }
}

export default AuthService;
