import { toJS } from 'mobx';
import moment from 'moment';
import { useRouter } from 'next/router';
import * as React from 'react';
import styled from '~/styled';
import useStores from '../../helpers/useStores';
import { ChatRoomListItemDto } from '../../service/ChatService';
import CircleImageView from '../CircleImageView';

interface IChatCardProps {
  chatData?: ChatRoomListItemDto;
}

export default (props: IChatCardProps) => {
  const {authStore} = useStores();
  const router = useRouter();
  const mnt = moment(props.chatData!.modifiedDateTime);
  const diffTime = moment.duration(mnt.diff(moment())).asHours();
  const handleClickChatItem = () => {
    router.push(`/chat/${props.chatData!.roomId}`);
  }
  return(
        <ChatListItem onClick={handleClickChatItem}>
          <CircleImageView
            size={2}
            src={
              'https://pngimage.net/wp-content/uploads/2018/05/default-user-profile-image-png-6.png'
            }
          />
          <WrapperText>
            <Line fontSize={14}>{authStore.auth!.sub === props.chatData!.buyerUser.userId ? props.chatData!.sellerUser.userName : props.chatData!.buyerUser.userName}</Line>
            <WrapperInnerText>
              <Line fontSize={10} float={'left'}>
                {props.chatData!.lastMessage}
              </Line>
              <Line fontSize={10} float={'right'}>
                {
                  diffTime >= -168 ? mnt.fromNow() : mnt.format('YY년 M월 D일')
                }
              </Line>
            </WrapperInnerText>
          </WrapperText>
        </ChatListItem>
    )
}

const ChatListItem = styled.div`
  display: flex;
  margin-top: 2vh;
    margin-bottom: 3vh;
  width:100%;
`;

const WrapperText = styled.div`
  margin-left: 2vw;
  width:100%;
  padding-bottom: 12px;
  border-bottom: 1px solid lightgray;
`;

const WrapperInnerText = styled.div`
  position: relative;
  width: 100%;
`;

interface IText {
  fontSize: number;
  float?: 'left' | 'right';
}

const Line = styled.div<IText>`
  font-size: ${(props) => props.fontSize + 'px'};
  float: ${(props) => props.float};
`;
