import * as React from 'react';
import { useGetSearchByCategoryQuery } from '~/generated/graphql';
import { IPostMetaOutput } from '~/generated/graphql';
import styled from '~/styled';
import category from '../../pages/category';
import Card from './CategoryCard';

interface IQueryData {
  searchThingsByCategory: IPostMetaOutput[];
}

interface ICardProps {
  categoryId: number;
}

export default (props: ICardProps) => {
  const { data, loading, error } = useGetSearchByCategoryQuery({
    variables: { input: props.categoryId },
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

  const { searchThingsByCategory } = data as IQueryData;

  if (Array.isArray(searchThingsByCategory)) {
    if (searchThingsByCategory.length === 0) {
      return <Wrapper><b>등록된 글이 없습니다</b></Wrapper>;
    }
    Cards = searchThingsByCategory!.map((item, i) => {
      if (searchThingsByCategory.length) {
        return <Card key={i} idx={i} cardData={item} loading={false} />;
      }
    });
  }

  return <Wrapper onClick={handleClickCard}>{Cards}</Wrapper>;
};

const Wrapper = styled.div`
  display: block;
  padding: 80px 16px 30px 16px;
  height: 100%;
`;
