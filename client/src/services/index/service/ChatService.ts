import axios, { AxiosResponse } from 'axios';
import {NEXT_APP_REST_ENDPOINT} from '../helpers/config';

export type LoginResponseDto = {
  token: string;
  id: number;
}

export type LoginSignupRequestDto = {
  email: string;
  password: string;
};

export type AuthResponseDto = {
  id: string;
  email: string;
  password: string;
}

const API_HOST = NEXT_APP_REST_ENDPOINT || 'http://localhost:5000/api';

class ChatService {
  async getTest(): Promise<AxiosResponse> {
      console.log(NEXT_APP_REST_ENDPOINT);
    return axios.get(`${API_HOST}/redis`);
  }

  async signUp(body: LoginSignupRequestDto): Promise<AxiosResponse<AuthResponseDto>> {
    return axios.post(`${API_HOST}/auth/signup`, body);
  }
}

export default ChatService;
