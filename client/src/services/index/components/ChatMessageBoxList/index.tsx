import * as React from 'react';
import styled from '~/styled';
import Client from './Client';
import Host from './Host';

const dump = [
  { userId: '#맥북', time: '1', type: '1' },
  { userId: '#맥북', time: '1', type: '1' },
  { userId: '#맥북', time: '1', type: '0' },
  { userId: '#맥북', time: '1', type: '1' },
  { userId: '#맥북', time: '2', type: '0' },
  { userId: '#맥북', time: '2', type: '0' },
  { userId: '#맥북', time: '2', type: '0' },
];

export default () => {
  const Cards = dump.map((item, i) => {
    return item.type === '1' ? 
        <Host key={i} cardData={item} />:
        <Client key={i} cardData={item} />
  });
  return <Wrapper>{Cards}</Wrapper>;
};

const Wrapper = styled.div`
`;
