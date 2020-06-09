import * as React from 'react';
import { useGetRecentQuery } from '~/generated/graphql';
import styled from '~/styled';
import Card from './ProductCard';

interface IQueryData {
  findRecentPosts: [IRecentPost];
}

export interface IRecentPost {
  category: string;
  postId: string;
  user: any;
  hashTags: object[] | any;
  isBuy: boolean;
  price: number;
  saleDate: string;
  imgUrls: object[] | any;
  deal: string;
  createdDate: string;
  modifiedDate: string;
}

interface ICardProps {
}

export default (props: ICardProps) => {
  const { data, loading, error } = useGetRecentQuery();
  let Cards;
  const handleClickCard = () => {
    console.log(findRecentPosts);
    console.log(error);
  };

  if (loading || error) {
    Cards = [1, 2, 3, 4, 5, 6].map((item, i) => {
      return <Card key={i} idx={i} cardData={null} loading />;
    });
    return <Wrapper onClick={handleClickCard}>{Cards}</Wrapper>;
  }

  const { findRecentPosts } = data as IQueryData;

  if (Array.isArray(findRecentPosts)) {
    Cards = data!.findRecentPosts!.map((item, i) => {
      if (data!.findRecentPosts) {
        return <Card key={i} idx={i} cardData={item} loading={false} />;
      }
    });
  }

  return <Wrapper onClick={handleClickCard}>{Cards}</Wrapper>;
};

const Wrapper = styled.div`
  display: block;
`;
