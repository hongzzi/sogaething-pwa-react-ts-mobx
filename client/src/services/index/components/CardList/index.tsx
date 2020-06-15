import { toJS } from 'mobx';
import { useObserver } from 'mobx-react';
import * as React from 'react';
import {
  IFile,
  IHashtag,
  IUser,
  IUserHistoryResponse,
  Maybe,
  useGetHistoryQuery,
} from '~/generated/graphql';
import styled from '~/styled';
import useStores from '../../helpers/useStores';
import Card from './Card';

interface IQueryData {
  findUserHistoryByUserId: IFindUserHistoryByUserId[];
}

export interface IFindUserHistoryByUserId {
  user: IUser;
  postId: string;
  isBuy: boolean;
  title: string;
  saleDate: string;
  contents: string;
  viewCount: number;
  deal: string;
  createdDate: string;
  modifiedDate: string;
  hashTags: IHashtag[];
  price: number;
  imgUrls: IFile[];
}

function useCardData() {
  const { cardStore } = useStores();
  const { MainHistoryCards, MainHistoryLoading } = cardStore;
  return useObserver(() => ({
    // useObserver를 사용해서 리턴하는 값의 업데이트를 계속 반영한다
    MainHistoryCards,
    MainHistoryLoading,
  }));
}

export default () => {
  const { data, loading, error } = useGetHistoryQuery();
  const { MainHistoryCards} = useCardData();
  const { cardStore, authStore } = useStores();

  const handleCardClick = () => {};
  let Cards;
  if (loading) {
    Cards = [1, 2, 3].map((item, i) => {
      return <Card key={i} cardData={null} loading />;
    });
    return <Wrapper onClick={handleCardClick}>{Cards}</Wrapper>;
  }

  if (data) {
    cardStore.setHistoryCards(data);
  }

  if (
    Array.isArray(data!.findUserHistoryByUserId) &&
    data!.findUserHistoryByUserId.length !== 0
  ) {
    Cards = data!.findUserHistoryByUserId.map((item, i) => {
      return <Card key={i} cardData={item} loading={false} />;
    });
  }

  return (
    <Wrapper onClick={handleCardClick}>
      {Cards ? Cards : <HistoryText>띵한 중고물품을 구경해보세요</HistoryText>}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  display: inline-block;
  width: 100%;
  white-space: nowrap;
  overflow-x: scroll;
  ::-webkit-scrollbar {
    display: none;
  }
`;

const HistoryText = styled.div`
  text-align: center;
`;

const Line = styled.div`
  margin: 22px 0.5rem 0rem 0;
  font-size: 11px;
`;

const CategoryText = styled.p`
  display: inline-block;
  font-size: 12px;
  color: ${(props) => props.theme.mainCategoryTextColor};
`;
