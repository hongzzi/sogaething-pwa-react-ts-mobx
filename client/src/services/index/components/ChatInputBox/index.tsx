import { useObserver } from 'mobx-react';
import moment from 'moment';
import * as React from 'react';
import { TiPlus } from 'react-icons/ti';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import styled from '~/styled';
import gallery from '../../assets/img/chat-gallery.png?url';
import send from '../../assets/img/chat-send.png?url';
import { ENDPOINT } from '../../constants';
import { NEXT_APP_SOCKET_ENDPOINT } from '../../helpers/config';
import useStores from '../../helpers/useStores';
import { IChatDto } from '../../service/ChatService';
import CustomIcon from '../CustomIcon';

interface IChatInputProps {
  roomId: string;
  chatRoomData: IChatDto[];
}

function useChatData() {
  const { authStore, pageStore } = useStores();
  return useObserver(() => ({
    auth: authStore.auth,
    chatModal: pageStore.chatModal,
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

  const { auth, chatModal } = useChatData();
  const { chatStore, pageStore } = useStores();
  const [roomId, setRoomId] = React.useState(props.roomId);
  const [sender, setSender] = React.useState(auth ? auth.userId : '');
  const [message, setMessage] = React.useState('');
  const [bankName, setBankName] = React.useState('');
  const [bankAccountNo, setBankAccountNo] = React.useState('');
  const [amount, setAmount] = React.useState(0);

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

  //REMIT

  const handleSendDeposit = () => {
    ws.send(
      '/pub/chat/remit',
      {},
      JSON.stringify({type: 'REMIT', roomId, sender, message: {bankName, bankAccountNo, amount, message: sender}}),
    );
    setBankAccountNo('');
    setBankName('');
    setAmount(0);
    handlePlusClick();
  }

  const handlePlusClick = () => {
    pageStore.toggleChatModal();
  };

  const handleDepositClick = () => {
    const testData = {
      apiKey: '',
      bankName: '기업',
      bankAccountNo: '21604828802016',
      amount: 15000,
      message: '토스입금버튼',
    };
    chatStore.postDepostService(testData);
  };

  const handleChangeAccountNo = (e: React.ChangeEvent<HTMLInputElement>) => {
    const tv = e.target.value;
    const notIncludeWords = ['e', '-', '+'];
    if (notIncludeWords.includes(tv)) {
      return;
    }else{
      setBankAccountNo(tv);
    }
  };

  const handleChangeBankName = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setBankName(e.target.value);
  };

  const handleChangeAmount = (e: React.ChangeEvent<HTMLInputElement>) => {
    setAmount(Number.parseInt(e.target.value, 10));
  };

  const handleRecvMessage = (recv: IChatDto) => {
    const temp: IChatDto[] = [
      {
        type: recv.type,
        sender: recv.type === 'ENTER' ? '[알림]' : recv.sender,
        message: recv.message,
        createdDateTime: moment().format(),
        roomId: recv.roomId,
      },
    ];
    const res = temp.concat(chatStore.chatRoomData);
    chatStore.setChatRoomData(res);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setMessage(e.target.value);
  };

  let reconnect = 0;

  const stompDisconnect = () => {
    ws.disconnect(() => {
      console.log('disconnect');
    });
  };

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
  };

  return (
    <Wrapper>
      <StyledPlusIcon size={26} color={'#c5cee0'} onClick={handlePlusClick} />
      <CustomIcon url={gallery} />
      <Input value={message} onChange={handleInputChange} />
      <div onClick={handleSendMessage}>
        <CustomIcon url={send} />
      </div>
      <Modal modalState={chatModal}>
        <ModalItem>
          <ModalFlexItem>
            계좌번호{' '}
            <DepostInput
              value={bankAccountNo}
              type='number'
              onChange={handleChangeAccountNo}
            />
          </ModalFlexItem>
        </ModalItem>
        <ModalItem>
          <ModalFlexItem>
            은행
            <DepositSelect onChange={handleChangeBankName}>
              <option>기업</option>
              <option>우리</option>
              <option>농협</option>
              <option>신한</option>
            </DepositSelect>
          </ModalFlexItem>
        </ModalItem>
        <ModalItem>
          <ModalFlexItem>
            금액
            <DepostInput
              type='number'
              value={amount}
              onChange={handleChangeAmount}
            />
          </ModalFlexItem>
        </ModalItem>
        <ModalItem onClick={handleSendDeposit}>전송</ModalItem>
        <ModalItem onClick={handlePlusClick}>닫기</ModalItem>
      </Modal>
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

const StyledPlusIcon = styled(TiPlus)`
  margin-left: 6px;
`;

interface IModalProps {
  modalState: boolean;
}

const Modal = styled.div<IModalProps>`
  visibility: ${(props) => (props.modalState ? 'visible' : 'hidden')};
  position: fixed;
  bottom: 0;
  width: 100vw;
  height: 250px;
  background-color: rgba(255, 255, 255, 0.95);
  /* transition: visibility 1s ease-in; */
`;

const ModalFlexItem = styled.div`
  padding: 0 10vw 0 10vw;
  display: flex;
  align-content: center;
  justify-content: space-between;
  width: 100%;
`;

const DepostInput = styled.input`
  border: 1px solid lightgray;
  width: 45vw;
`;

const DepositSelect = styled.select`
  width: 45vw;
`;

const ModalItem = styled.div`
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: space-around;
  height: 50px;
  width: 100%;
  border-top: 1px solid ${(props) => props.theme.bolderColor};
`;
