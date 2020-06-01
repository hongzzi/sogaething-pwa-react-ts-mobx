import { toJS } from 'mobx';
import { useObserver } from 'mobx-react';
import * as React from 'react';
import styled from '~/styled';
import CategoryHeader from '../../components/CategoryHeader';
import ChatList from '../../components/ChatList';
import Nav from '../../components/Nav';
import useStores from '../../helpers/useStores';
import { MarginTopCategoryHeaderContainer } from '../matchresult/[id]';

function useChatData() {
  const { chatStore } = useStores()
  return useObserver(() => ({ // useObserver를 사용해서 리턴하는 값의 업데이트를 계속 반영한다
    chatRooms: chatStore.chatRooms,
    loading: chatStore.loading,
    getUserChatList: chatStore.getUserChatList,
  }))
}

export default () => {
  const {chatRooms, loading, getUserChatList} = useChatData();
  const {authStore} = useStores();
  React.useEffect(() => {
    getUserChatList(authStore.getAuth()!.sub);
  }, [])

  const handleOnClick = () => {
    console.log(chatRooms);
  }

  return (
    <Wrapper>
      <CategoryHeader type={'chat'} />
      <MarginTopCategoryHeaderContainer onClick={handleOnClick}>
        <ChatList chatData={chatRooms} loading={loading} />
      </MarginTopCategoryHeaderContainer>
      <Nav />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  padding-bottom: 48px;
`;

const ChatListItem = styled.div`
  display: flex;
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
