import { toJS } from 'mobx';
import { useObserver } from 'mobx-react';
import { useRouter } from 'next/router';
import * as React from 'react';
import CategoryHeader from '~/services/index/components/CategoryHeader';
import ChatInputBox from '~/services/index/components/ChatInputBox';
import ChatMessageBox from '~/services/index/components/ChatMessageBoxList';
import useStores from '~/services/index/helpers/useStores';
import styled from '~/styled';

function useChatData() {
  const { chatStore } = useStores();
  return useObserver(() => ({
    // useObserver를 사용해서 리턴하는 값의 업데이트를 계속 반영한다
    loading: chatStore.loading,
    chatRoomData: chatStore.chatRoomData,
    chatRoomAuth: chatStore.chatRoomAuth,
  }));
}

export default () => {
  const { chatStore, authStore } = useStores();
  const { loading, chatRoomData, chatRoomAuth } = useChatData();
  const router = useRouter();
  const [me, setMe] = React.useState('');
  const [userId, setUserId] = React.useState('');

  const messageEndRef = React.useRef<HTMLDivElement>(null);

  React.useEffect(() => {
    chatStore.getUserChatRoom(router.query.id);
    if (authStore.getAuth()!.userName) {
      setMe(authStore.getAuth()!.userName);
      setUserId(authStore.getAuth()!.userId + '');
    }
  }, []);

  const scrollToBotton = () => {
    messageEndRef.current!.scrollTo(messageEndRef.current!.scrollHeight, messageEndRef.current!.scrollHeight);
  }

  React.useEffect(scrollToBotton, [chatRoomData!.length]);

  return (
    <Wrapper>
      {loading && <CategoryHeader type={'normal'} text={''} />}
      {!loading && (
        <CategoryHeader
          type={'normal'}
          text={
            chatStore.getChatRoomAuth().buyer.name === me
              ? chatStore.getChatRoomAuth().seller.name
              : chatStore.getChatRoomAuth().buyer.name
          }
        />
      )}
      <ChatContainer ref={messageEndRef}>
        <WrapperChatMessage>
          <ChatMessageBox
            chatRoomData={chatRoomData}
            me={userId}
            imgPath={
              chatStore.getChatRoomAuth().buyer.name === me
                ? chatStore.getChatRoomAuth().seller.imageUrl
                : chatStore.getChatRoomAuth().buyer.imageUrl
            }
          />
        </WrapperChatMessage>
      </ChatContainer>
      <ChatInput>
        <ChatInputBox roomId={router.query.id} chatRoomData={chatRoomData} />
      </ChatInput>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: grid;
  position: relative;
  grid-auto-rows: 56px minmax(0vh, 88vh) 65px;
  height: 100vh;
  background-color: #fafafa;
  grid-template-areas:
    "CH"
    "CC"
    "CI";
`;

const ChatContainer = styled.div`
  grid-area: CC;
  overflow-y: scroll;
  background-color: inherit;
  /* display: flex;
  flex-direction: column;
  justify-content: end; */
  position: absolute;
  bottom: 0px;
  width: 100%;
  min-height: 0px;
  max-height: 100%;
  padding: 3vw;
`;

const WrapperChatMessage = styled.div`
  display: flex;
  flex-direction: column-reverse;
  justify-content: space-between;
  -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
  &::-webkit-scrollbar{
    display: none;
  }
`;

const ChatInput = styled.div`
  grid-area: CI;
  height: 100%;
  background-color: white;
`;
