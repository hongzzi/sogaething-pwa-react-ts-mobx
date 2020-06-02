import * as React from 'react';
import useStores from '../helpers/useStores';

export interface IUserProps {}

export default function User() {
  const store = useStores();

  store.authStore.setEmail('123123');
  return (
    <div>
      User
      {store.authStore.email}
    </div>
  );
}
