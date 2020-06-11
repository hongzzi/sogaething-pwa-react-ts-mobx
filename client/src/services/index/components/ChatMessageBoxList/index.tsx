import { useRouter } from 'next/router';
import * as React from 'react';
import styled from '~/styled';
import useStores from '../../helpers/useStores';
import { IChatDto } from '../../service/ChatService';
import Client from './Client';
import Host from './Host';

interface IChatMessageProps {
  chatRoomData: IChatDto[];
  me: string;
  imgPath: string;
}

export default (props: IChatMessageProps) => {
  let Cards;
  if(Array.isArray(props.chatRoomData)){
    Cards = props.chatRoomData.map((item, i) => {
      return item.sender !== props.me ?
          <Host key={i} cardData={item} imgPath={props.imgPath}/> :
          <Client key={i} cardData={item} />
    });
  }else {
    Cards = '채팅을 시작해보세요!'
  }
  
  return <>{Cards}</>;
};
