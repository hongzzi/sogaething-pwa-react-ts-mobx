import * as React from 'react';
import styled from '~/styled';
import Card from './ChatItem';

const dump = [
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
  { name: '#맥북', chat: '1.000,000' },
];

export default () => {
  const Cards = dump.map((item, i) => {
    return <Card key={i} />;
  });
  return <Wrapper>{Cards}</Wrapper>;
};

const Wrapper = styled.div`
  position: relative;
  width: 100%;
  white-space: nowrap;
  overflow-y: scroll;
  ::-webkit-scrollbar {
    display: none;
  }
`;
