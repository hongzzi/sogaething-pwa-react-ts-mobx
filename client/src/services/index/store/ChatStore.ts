import autobind from 'autobind-decorator';
import { action, observable, reaction } from 'mobx';
import Stomp, {Client} from 'stompjs';
import ChatService from '../service/ChatService';
import { ChatRoomListItemDto, IChatDto, IChatRoomAuthDto, ICreateChatRoomRequestDto, ICreateChatRoomResponseDto } from './../service/ChatService';

@autobind
class ChatStore {
  @observable chatRooms: ChatRoomListItemDto[] = [];
  @observable loading: boolean = false;
  @observable inputText: string = '';
  @observable chatRoom: number = 0;
  @observable chatRoomData: IChatDto[] = [];
  @observable chatRoomAuth: IChatRoomAuthDto = {
    seller: {
      imageUrl: '',
      name: '',
    },
    buyer: {
      imageUrl: '',
      name: '',
    },
  };
  @observable ws: Client|null = null;
  @observable createdChat: ICreateChatRoomResponseDto = {
    content: '',
    name: '',
    state: '',
  }
  constructor(private chatService: ChatService, root: any) {}

  @action
  getTest() {
    return this.chatService.getTest();
  }

  @action
  async getUserChatList(authId: number) {
    this.setLoading(true);
    const response = await this.chatService.getUserChatList(authId);
    const chatArr = response.data;
    this.setChatRooms(chatArr);
    this.setLoading(false);
  }

  @action
  async getUserChatRoom(roomId: number) {
    this.loading = true;
    const response = await this.chatService.getUserChatRoom(roomId);
    const data = response.data;
    this.setChatRoomData(data.chatMessages);
    this.setChatRoom(roomId);
    this.setChatRoomAuth(data.chatRoom);
    this.loading = false;
  }

  @action
  async postCreateChatRoom(data: ICreateChatRoomRequestDto ) {
    this.loading = true;
    const response = await this.chatService.postChatRoom(data);
    this.createdChat = response.data;
    this.loading = false;
    return response;
  }

  @action
  setWs(ws: Client) {
    this.ws = ws;
  }

  @action
  setChatRoomAuth(cra: IChatRoomAuthDto) {
    this.chatRoomAuth = cra;
  }

  @action
  getChatRoomAuth() {
    return this.chatRoomAuth;
  }

  @action
  setChatRoom(cr: number) {
    this.chatRoom = cr;
  }

  @action
  setChatRoomData(crd: IChatDto[]) {
    this.chatRoomData = crd;
  }

  @action
  getChatRoomData() {
    return this.chatRoomData;
  }

  @action
  setChatRooms(crs: ChatRoomListItemDto[]) {
    this.chatRooms = crs;
  }

  @action
  setLoading(b: boolean) {
      this.loading = b;
  }
}

export default ChatStore;
