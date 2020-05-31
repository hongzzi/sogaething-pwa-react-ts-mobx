import autobind from 'autobind-decorator';
import { action, observable, reaction } from 'mobx';
import ChatService from '../service/ChatService';
import { ChatRoomListItemDto } from './../service/ChatService';

@autobind
class ChatStore {
  @observable nav: boolean = false;
  @observable clickedIdx: number = 0;
  @observable chatRooms: ChatRoomListItemDto[] = [];
  @observable loading: boolean = false;

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
    this.setLoading(false);
    this.setChatRomms(chatArr);
  }

  @action
  setChatRomms(crs: ChatRoomListItemDto[]) {
    this.chatRooms = crs;
  }

  @action
  setLoading(b: boolean) {
      this.loading = b;
  }
}

export default ChatStore;
