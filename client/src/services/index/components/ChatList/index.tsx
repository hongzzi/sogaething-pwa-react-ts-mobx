import { toJS } from 'mobx';
import * as React from 'react';
import styled from '~/styled';
import { ChatRoomListItemDto } from '../../service/ChatService';
import Card from './ChatItem';

interface IChatCardProps {
  chatData: ChatRoomListItemDto[];
  loading: boolean;
}

export default (props: IChatCardProps) => {
  let Cards;
  if (Array.isArray(props.chatData)) {
    Cards = props.chatData.map((item, i) => {
      return <Card key={i} chatData={item} />;
    });
  } else {
    Cards = '채팅을 시작해보세요!'
  }

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
