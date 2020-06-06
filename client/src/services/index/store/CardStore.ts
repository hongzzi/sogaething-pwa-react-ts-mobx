import autobind from 'autobind-decorator';
import { action, autorun, observable, reaction, toJS } from 'mobx';
import {
  IGetHistoryQuery,
  IUserHistoryResponse,
} from './../../../generated/graphql';
import { IFindUserHistoryByUserId } from './../components/CardList/index';
import { IFindUserInfo } from './../components/MainUserCard/index';
import { DataFetchLoading } from './type';

@autobind
class CardStore {
  @observable MainUserCard: IFindUserInfo = {
    address: '',
    name: '',
    numOfPosts: 0,
    trust: 0,
    imgurl: '',
  };
  @observable MainUserLoading: boolean = false;
  @observable MainHistoryCards: IGetHistoryQuery = {
    findUserHistoryByUserId: [
      {
        postId: 'string',
        isBuy: false,
        title: '',
        saleDate: '',
        viewCount: 0,
        deal: '',
        createdDate: '',
        modifiedDate: '',
        hashTags: [],
        price: 0,
        imgUrls: [],
      },
    ],
  };
  @observable MainHistoryLoading: boolean = false;
  @observable MainRecentCards: object = {};

  constructor(root: any, initialData?: CardStore) {
    if (initialData) {
        this.MainHistoryCards = initialData.MainHistoryCards;
    } else {
        this.MainHistoryCards = {
            findUserHistoryByUserId: [
                {
                  postId: 'string',
                  isBuy: false,
                  title: '',
                  saleDate: '',
                  viewCount: 0,
                  deal: '',
                  createdDate: '',
                  modifiedDate: '',
                  hashTags: [],
                  price: 0,
                  imgUrls: [],
                },
              ],
        }
    }
  }

  @action
  setMainUserCard(data: IFindUserInfo) {
    this.MainUserCard = data;
  }

  @action
  setHistoryCards(data: IGetHistoryQuery) {
    this.MainHistoryCards = data;
  }
}

export default CardStore;
