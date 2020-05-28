import * as React from 'react';
import styled from '~/styled';
import { Container } from '..';
import CategoryHeader from '../../components/CategoryHeader';
import CircleImageView from '../../components/CircleImageView';
import Nav from '../../components/Nav';
import ChatList from '../../components/ChatList';

export default () => {
  return (
    <Wrapper>
      <CategoryHeader type={'chat'} />
      <Container>
        <ChatList />
      </Container>
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
  float: ${props => props.float};
`;
