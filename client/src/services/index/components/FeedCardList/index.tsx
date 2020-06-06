import * as React from 'react';
import styled from '~/styled';
import Card from './FeedCard';

export interface IFeedCardListProps {
  data: any[],
}

export default (props: IFeedCardListProps) => {
  const { data } = props;
  const Cards = data.map((item, i) => {
    return <Card key={i} data={item}/>;
  });
  return <Wrapper>{Cards}</Wrapper>;
};

const Wrapper = styled.div`
`;
