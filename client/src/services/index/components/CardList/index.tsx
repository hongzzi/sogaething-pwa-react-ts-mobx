import * as React from 'react';
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

export default () => {
  const Cards = dump.map((item, i) => {
    return <Card key={i} />;
  });
  return <Wrapper>{Cards}</Wrapper>;
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
