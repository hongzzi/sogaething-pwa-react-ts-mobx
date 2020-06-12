import { useStaticRendering } from 'mobx-react';
import BankService from '../service/BankService';
import ChatService from '../service/ChatService';
import AuthStore, { IAuth, initialAuth, IToken } from './AuthStore';
import CardStore from './CardStore';
import ChatStore from './ChatStore';
import MatchStore, { initialMatch } from './MatchStore';
import PageStore, { initialPage } from './PageStore';
import PostStore, { initialPost } from './PostStore';
import VisiableStore from './VisiableStore';

const isServer = typeof window === 'undefined';

useStaticRendering(isServer);

export interface IEnvironments {
  [key: string]: string;
}

let store: RootStore | null = null;

export class RootStore {
  authStore: AuthStore;
  pageStore: PageStore;
  postStore: PostStore;
  chatStore: ChatStore;
  matchStore: MatchStore;
  cardStore: CardStore;
  visiableStore: VisiableStore;

  constructor(initialData?: any) {
    this.authStore = new AuthStore(this, initialData ? initialData.authStore : null);
    this.pageStore = new PageStore(this, initialData ? initialData.pageStore : null);
    this.postStore = new PostStore(this, initialData ? initialData.postStore : null);
    this.chatStore = new ChatStore(new ChatService(), new BankService(), this);
    this.matchStore = new MatchStore(this, initialData ? initialData.matchStore : null);
    this.cardStore = new CardStore(this, initialData ? initialData.cardStore : null);
    this.visiableStore = new VisiableStore(this, initialData ? initialData.visiableStore : null);
  }
}

export default function initializeStore() {
  if (isServer) {
    return new RootStore();
  }
  if (store === null) {
    store = new RootStore();
  }
  return store;
}
