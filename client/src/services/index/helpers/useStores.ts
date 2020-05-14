import { MobXProviderContext } from 'mobx-react';
import React from 'react';
import { RootStore } from './../store/index';

function useStores(): RootStore {
  return React.useContext(MobXProviderContext).value;
}

export default useStores;
