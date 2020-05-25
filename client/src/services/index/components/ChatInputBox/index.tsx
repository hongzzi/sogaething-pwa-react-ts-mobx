import * as React from 'react';
import styled from '~/styled';
import gallery from '../../assets/img/chat-gallery.png?url';
import send from '../../assets/img/chat-send.png?url';
import CustomIcon from '../CustomIcon';

export default () => {
  return (
    <Wrapper>
      <CustomIcon url={gallery} />

      <Input />
      <CustomIcon url={send} />
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
