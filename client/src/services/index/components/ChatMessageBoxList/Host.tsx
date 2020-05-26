import * as React from 'react';
import styled from '~/styled';
import CircleImageView from '../CircleImageView';

interface IChatMessageBox {
  cardData: {
    userId: string | number; // will be check type
    time: string;
  };
}

export default (props: IChatMessageBox) => {
  const { userId, time } = props.cardData;
  return (
    <Wrapper>
      <CircleImageView
        size={1.8}
        src='https://pngimage.net/wp-content/uploads/2018/05/default-user-profile-image-png-6.png'
      />
      <Message>123123</Message>
      <WrapperTime>
        <Time>{time}</Time>
      </WrapperTime>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  display: flex;
  width: 100%;
  margin: 2vw;

  & > img {
    margin-right: 2vw;
  }
`;

const Message = styled.div`
  font-size: 12px;
  min-height: 10px;
  max-height: 300px;
  min-width: 30px;
  max-width: 60vw;

  padding: 8px;

  background-color: #fafafa;

  border-radius: 5px;
`;

const WrapperTime = styled.div`
  height: 100%;
  align-self: flex-end;
`;

const Time = styled.p`
  color: #b7b7b7;
  vertical-align: bottom;
  font-size: 0.5rem;
  margin:0;
  margin-left: 1vw;
`;
