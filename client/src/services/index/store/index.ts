import { useStaticRendering } from 'mobx-react';
import AuthStore, {IAuth, initialAuth} from './AuthStore';
import PageStore, {initialPage} from './PageStore';
const isServer = typeof window === 'undefined';

useStaticRendering(isServer);

export interface IEnvironments {
  [key: string]: string
}

let store: RootStore | null = null;

const initialRoot = {
  authStore: initialAuth,
  pageStore: initialPage,
};

export class RootStore {
  authStore: AuthStore;
  pageStore: PageStore;
  constructor(initialData: any) {
    if (isServer) {
      this.authStore = new AuthStore(initialData.authStore, this);
    } else {
      this.authStore = new AuthStore({...initialData.authStore, token: window.sessionStorage.getItem('jwt')}, this);
    }
    this.pageStore = new PageStore(initialData.pageStore, this);
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
