import * as React from 'react';
import styled from '~/styled';
import { ChatRoomListItemDto } from '../../service/ChatService';
import Card from './ChatItem';

interface IChatCardProps {
  chatData: ChatRoomListItemDto[];
  loading: boolean;
}

export default (props: IChatCardProps) => {
  const Cards = props.chatData.map((item, i) => {
    return <Card key={i} chatData={item} />;
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
