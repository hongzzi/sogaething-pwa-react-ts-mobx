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
  React.useEffect(() => {
    chatStore.getUserChatRoom(router.query.id);
    setMe(authStore.getAuth()!.userName);
    setUserId(authStore.getAuth()!.userId + '');
  }, []);

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
      <ChatContainer>
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
  grid-auto-rows: 56px minmax(0vh, 85vh) 60px;
  height: 100%;
  grid-template-areas:
    "CH"
    "CC"
    "CI";
`;

const ChatContainer = styled.div`
  grid-area: CC;
  overflow-y: scroll;
  background-color: #fafafa;
  padding: 3vw;
`;

const WrapperChatMessage = styled.div`
  display: flex;
  flex-direction: column-reverse;
  justify-content: space-between;
`;

const ChatInput = styled.div`
  grid-area: CI;
  height: 100%;
  background-color: white;
`;
