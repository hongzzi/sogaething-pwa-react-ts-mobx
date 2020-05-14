import { useStaticRendering } from 'mobx-react';
import AuthStore, {IAuth, initialAuth} from './AuthStore';

const isServer = typeof window === 'undefined';

useStaticRendering(isServer);

let store: RootStore | null = null;

const initialRoot = {
  authStore: initialAuth,
};

export class RootStore {
  authStore: AuthStore;

  constructor(initialData: any ) {
    this.authStore = new AuthStore(initialData.authStore, this);
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
