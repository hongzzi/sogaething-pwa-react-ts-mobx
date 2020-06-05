import * as React from 'react';
import styled from '~/styled';
import Card from './MatchCard';

export interface IMatchCardListProps {
  data: any[],
}

export default (props: IMatchCardListProps) => {
  const { data } = props;
  const Cards = data.map((item, i) => {
    return <Card key={i} match={item} />;
  });
  return <>{Cards}</>;
};

const Wrapper = styled.div`
`;
