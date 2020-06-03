import { useObserver } from 'mobx-react';
import moment from 'moment';
import * as React from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import styled from '~/styled';
import gallery from '../../assets/img/chat-gallery.png?url';
import send from '../../assets/img/chat-send.png?url';
import { NEXT_APP_SOCKET_ENDPOINT } from '../../helpers/config';
import useStores from '../../helpers/useStores';
import { IChatDto } from '../../service/ChatService';
import CustomIcon from '../CustomIcon';
import { ENDPOINT } from '../../constants';

interface IChatInputProps {
  roomId: string;
  chatRoomData: IChatDto[];
}

function useChatData() {
  const { authStore } = useStores();
  return useObserver(() => ({
    auth: authStore.auth,
  }));
}

export default (props: IChatInputProps) => {
  let sockJS;
  if (NEXT_APP_SOCKET_ENDPOINT) {
    sockJS = new SockJS(NEXT_APP_SOCKET_ENDPOINT!);
  } else {
    sockJS = new SockJS(ENDPOINT.SOCKET);
  }
  let ws = Stomp.over(sockJS);

  const { auth } = useChatData();
  const {chatStore} = useStores();
  const [roomId, setRoomId] = React.useState(props.roomId);
  const [sender, setSender] = React.useState(auth ? auth.userId : '');
  const [message, setMessage] = React.useState('');

  React.useEffect(() => {
    connect();
  }, []);

  const handleSendMessage = () => {
    ws.send(
      '/pub/chat/message',
      {},
      JSON.stringify({ type: 'TALK', roomId, sender, message }),
    );
    setMessage('');
  };

  const handleRecvMessage = (recv: IChatDto) => {
    const temp: IChatDto[] = [{
      type: recv.type,
      sender: recv.type === 'ENTER' ? '[알림]' : recv.sender,
      message: recv.message,
      createdDateTime: moment().format(),
      roomId: recv.roomId,
    }];
    const res = temp.concat(chatStore.chatRoomData);
    chatStore.setChatRoomData(res);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setMessage(e.target.value);
  };

  let reconnect = 0;

  const stompDisconnect = () => {
    ws.disconnect(() => {console.log('disconnect')});
  }

  const connect = () => {
    // pub/sub event
    ws.connect(
      {},
      (frame) => {
        ws.subscribe('/sub/chat/room/' + roomId, function(message) {
          const recv = JSON.parse(message.body);
          handleRecvMessage(recv);
        });
        ws.send(
          '/pub/chat/message',
          {},
          JSON.stringify({ type: 'ENTER', roomId, sender }),
        );
      },
      (error) => {
        if (reconnect++ <= 5) {
          setTimeout(function() {
            console.log('connection reconnect');
            sockJS = new SockJS('/ws-stomp');
            ws = Stomp.over(sockJS);
            connect();
          }, 10 * 1000);
        }
      },
    );
  }
  return (
    <Wrapper>
      <CustomIcon url={gallery} />
      <Input value={message} onChange={handleInputChange} />
      <div onClick={handleSendMessage}>
        <CustomIcon url={send} />
      </div>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
`;

const Input = styled.textarea`
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  resize: none;
  outline: none;
  padding: 2vw;
  min-width: 70%;
  max-width: 85%;
  height: 35px;
  border-radius: 5px;
  box-shadow: inset 0 1px 3px 0 rgba(218, 218, 218, 0.5);
  background-color: #fcfcfc;
`;
