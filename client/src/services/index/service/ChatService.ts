import axios, { AxiosResponse } from 'axios';
import { NEXT_APP_REST_ENDPOINT } from '../helpers/config';

export interface ChatUserDto {
  userName: string;
  userId: string | number;
}

export interface ChatRoomListItemDto {
  buyerUser: ChatUserDto;
  sellerUser: ChatUserDto;
  createDateTime: string;
  isBuyerExit: boolean;
  isSellerExit: boolean;
  lastMessage: string;
  modifiedDateTime: string;
  roomId: number;
}

export interface AuthResponseDto {
  id: string;
  email: string;
  password: string;
}

const API_HOST = NEXT_APP_REST_ENDPOINT || 'http://localhost:5000/api';

class ChatService {
  async getTest(): Promise<AxiosResponse> {
    return axios.get(`${API_HOST}/redis`);
  }

  async getUserChatList(
    authId: number,
  ): Promise<AxiosResponse<ChatRoomListItemDto[]>> {
    return axios.get(`${API_HOST}/rooms/${authId}`);
  }
}

export default ChatService;
