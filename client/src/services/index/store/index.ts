import { useStaticRendering } from 'mobx-react';
import ChatService from '../service/ChatService';
import AuthStore, {IAuth, initialAuth} from './AuthStore';
import ChatStore from './ChatStore';
import PageStore, {initialPage} from './PageStore';
import PostStore, {initialPost} from './PostStore';
const isServer = typeof window === 'undefined';

useStaticRendering(isServer);

export interface IEnvironments {
  [key: string]: string
}

let store: RootStore | null = null;

export const initialRoot = {
  authStore: initialAuth,
  pageStore: initialPage,
  postStore: initialPost,
};

export class RootStore {
  authStore: AuthStore;
  pageStore: PageStore;
  postStore: PostStore;
  chatStore: ChatStore;

  constructor(initialData: any) {
    if (isServer) {
      this.authStore = new AuthStore(initialData.authStore, this);
    } else {
      this.authStore = new AuthStore({...initialData.authStore, token: window.sessionStorage.getItem('jwt')}, this);
    }
    this.pageStore = new PageStore(initialData.pageStore, this);
    this.postStore = new PostStore(initialData.postStore, this);
    this.chatStore = new ChatStore(new ChatService(), this);
  }
}

export default function initializeStore(initialData = initialRoot) {
  if (isServer) {
    return new RootStore(initialData);
  }
  if (store === null) {
    store = new RootStore(initialData);
  }
  return store;
}
