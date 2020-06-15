import { IDepositRequestDto } from './BankService';
import axios, { AxiosResponse } from 'axios';
import { NEXT_APP_REST_ENDPOINT } from '../helpers/config';
import { ENDPOINT } from './../constants';

export interface ChatUserDto {
  userName: string;
  userId: string;
  imageUrl: string;
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

export interface IChatRoomAuthDto {
  seller: {
    imageUrl: string;
    name: string;
  };
  buyer: {
    imageUrl: string;
    name: string;
  };
}

export interface IChatDto {
  type: string;
  roomId: number;
  sender: string;
  message: string | Omit<IDepositRequestDto, 'apiKey'>;
  createdDateTime: string;
}

export interface IChatRoomResponseDto {
  chatMessages: IChatDto[];
  chatRoom: IChatRoomAuthDto;
}

export interface ICreateChatRoomRequestDto {
  buyerId: string;
  sellerId: string;
  postId: string;
}

export interface ICreateChatRoomResponseDto {
  content: string; // 방번호
  name: string; // createChatroom
  state: string; // success
}

const API_HOST = NEXT_APP_REST_ENDPOINT || ENDPOINT.REST;

class ChatService {
  async getTest(): Promise<AxiosResponse> {
    return axios.get(`${API_HOST}/redis`);
  }

  async getUserChatList(
    authId: number,
  ): Promise<AxiosResponse<ChatRoomListItemDto[]>> {
    return axios.get(`${API_HOST}/rooms/${authId}`);
  }

  async getUserChatRoom(
    roomId: number,
  ): Promise<AxiosResponse<IChatRoomResponseDto>> {
    return axios.get(`${API_HOST}/message/${roomId}`);
  }

  async postChatRoom(
    data: ICreateChatRoomRequestDto,
  ): Promise<AxiosResponse<ICreateChatRoomResponseDto>> {
    return axios.post(`${API_HOST}/room`, data);
  }
}

export default ChatService;
