import * as React from 'react';
import { useGetSearchByCategoryQuery } from '~/generated/graphql';
import { IPostMetaOutput } from '~/generated/graphql';
import styled from '~/styled';
import category from '../../pages/category';
import Card from './CategoryCard';

interface IQueryData {
  findRecentPosts: [IPostMetaOutput];
}

interface ICardProps {}

export default (props: ICardProps) => {
  const { data, loading, error } = useGetSearchByCategoryQuery({
    variables: { input: '1' },
  });
  const handleClickCard = () => {
    console.log(data);
    console.log(error);
  };

  let Cards;
  if (loading || error) {
    Cards = [1, 2, 3, 4, 5, 6].map((item, i) => {
      return <Card key={i} idx={i} cardData={null} loading />;
    });
    return <Wrapper onClick={handleClickCard}>{Cards}</Wrapper>;
  }

  // const {  } = data;

  // if (Array.isArray(findRecentPosts)) {
  //   Cards = data!.findRecentPosts!.map((item, i) => {
  //     if (data!.findRecentPosts) {
  //       return <Card key={i} idx={i} cardData={item} loading={false} />;
  //     }
  //   });
  // }

  return <Wrapper onClick={handleClickCard}>{Cards}</Wrapper>;
};

const Wrapper = styled.div`
  display: block;
  padding: 60px 16px 0 16px;
`;
