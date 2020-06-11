import autobind from 'autobind-decorator';
import { action, observable, reaction } from 'mobx';

export interface IPage {
  nav: boolean;
  clickedIdx: number;
}

export const initialPage: IPage = {
  nav: true,
  clickedIdx: 0,
};

@autobind
class PageStore {
  @observable nav: boolean = false;
  @observable clickedIdx: number = 0;
  @observable modal: boolean = false;
  @observable chatModal: boolean = false;

  constructor(root: any, initialData?: PageStore) {
    if (initialData) {
      this.nav = initialData!.nav;
      this.clickedIdx = initialData!.clickedIdx;
    } else {
      this.nav = false;
      this.clickedIdx = 0;
    }

  }

  @action
  getClickedIdx() {
    return this.clickedIdx;
  }

  @action
  setClickedIdx(idx: number) {
    this.clickedIdx = idx;
  }

  @action
  toggleModal() {
    this.modal = !this.modal;
  }

  @action
  toggleChatModal(){
    this.chatModal = !this.chatModal;
  }
}

export default PageStore;
