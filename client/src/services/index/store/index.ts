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
  environments: [],
};

export class RootStore {
  authStore: AuthStore;
  pageStore: PageStore;
  environments: IEnvironments;

  constructor(initialData: any ) {
    this.authStore = new AuthStore(initialData.authStore, this);
    this.pageStore = new PageStore(initialData.pageStore, this);
    this.environments = initialData.env;
  }

  setEnv(env: IEnvironments) {
    this.environments = env;
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
