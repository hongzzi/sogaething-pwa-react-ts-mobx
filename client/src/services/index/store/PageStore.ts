import autobind from 'autobind-decorator';
import { action, observable, reaction } from 'mobx';

export interface IPage {
  nav: boolean;
}

export const initialPage: IPage = {
  nav : true,
};

@autobind
class PageStore {
  @observable nav: boolean = false;
  @observable clickedIdx: number = 0;

  constructor(initialData = initialPage, root: any) {
      this.nav = initialData.nav;
  }
}

export default PageStore;
