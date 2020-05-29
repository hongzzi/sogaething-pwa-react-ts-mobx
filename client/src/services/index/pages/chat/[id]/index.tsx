import { useRouter } from 'next/router';
import * as React from 'react';
import CategoryHeader from '~/services/index/components/CategoryHeader';
import ChatInputBox from '~/services/index/components/ChatInputBox';
import ChatMessageBox from '~/services/index/components/ChatMessageBoxList';
import styled from '~/styled';

export default () => {
  const router = useRouter();
  
  return (
    <Wrapper>
      <CategoryHeader type={'normal'} text={router.query.id} />
      <ChatContainer>
          <ChatMessageBox />
      </ChatContainer>
      <ChatInput>
          <ChatInputBox />
      </ChatInput>
    </Wrapper>
  );
};

const Wrapper = styled.div`
    display: grid;
    grid-auto-rows: 56px minmax(75vh, 85vh) 60px;
    height: 100%;
    grid-template-areas:
    "CH"
    "CC"
    "CI"
    ;
`;

const ChatContainer = styled.div`
    grid-area: CC;
    height: 100%;
    background-color: #f1f1f1;
    padding: 3vw;
    overflow-y: scroll;
`;

const ChatInput = styled.div`
    grid-area: CI;
    height:100%;
    background-color: white;
`;
