import { useRouter } from 'next/router';
import * as React from 'react';
import styled from '~/styled';
import useStores from '../../helpers/useStores';
import Client from './Client';
import Host from './Host';
import { IChatDto } from '../../service/ChatService';

interface IChatMessageProps {
  chatRoomData : IChatDto[];
  me: string;
}

export default (props: IChatMessageProps) => {
  const Cards = props.chatRoomData.map((item, i) => {
    return item.sender !== props.me ?
        <Host key={i} cardData={item} /> :
        <Client key={i} cardData={item} />
  });
  return <>{Cards}</>;
};
