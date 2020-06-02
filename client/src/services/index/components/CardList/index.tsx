import * as React from 'react';
import { useGetHistoryQuery } from '~/generated/graphql';
import styled from '~/styled';
import Card from './Card';

const dump = [
  { title: '#맥북', price: '1.000,000' },
  { title: '#맥북', price: '1.000,000' },
  { title: '#맥북', price: '1.000,000' },
  { title: '#맥북', price: '1.000,000' },
  { title: '#맥북', price: '1.000,000' },
  { title: '#맥북', price: '1.000,000' },
  { title: '#맥북', price: '1.000,000' },
];

interface IQueryData {
  findUserHistoryByUserId: [IFindUserHistoryByUserId];
}

export interface IFindUserHistoryByUserId {
  user: any;
  postId: string;
  isBuy: boolean;
  title: string;
  saleDate: string;
  contents: string;
  viewCount: number;
  deal: string;
  createdDate: string;
  modifiedDate: string;
  hashTags: object[] | any;
  price: number;
  imgUrls: object[] | any;
}

export default () => {
  const { data, loading, error } = useGetHistoryQuery();

  const {findUserHistoryByUserId} = data as IQueryData;

  const handleCardClick = () => {
    console.log(findUserHistoryByUserId);
    console.error(error);
  };

  const Cards = dump.map((item, i) => {
    if(loading){
      return <Card key={i} cardData={null} loading />;
    }else {
      return <Card key={i} cardData={findUserHistoryByUserId[i]} loading={false} />
    }
    
  });
  return <Wrapper onClick={handleCardClick}>{Cards}</Wrapper>;
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
