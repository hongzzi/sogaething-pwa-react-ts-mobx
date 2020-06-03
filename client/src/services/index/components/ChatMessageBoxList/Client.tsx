import moment from 'moment';
import * as React from 'react';
import styled from '~/styled';
import { IChatDto } from '../../service/ChatService';

interface IChatMessageBox {
  cardData: IChatDto;
}

export default (props: IChatMessageBox) => {
  const { sender, createdDateTime, message, type } = props.cardData;
  const mnt = moment(createdDateTime);
  return (
    <Wrapper>
      {type !== 'ENTER' ? (
        <>
          <Message>{message}</Message>
          <WrapperTime>
            <Time>{mnt.fromNow()}</Time>
          </WrapperTime>
        </>
      ) : (
        <Notice>{message}</Notice>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  display: flex;
  margin: 2vw;
  flex-direction: row-reverse;
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

  background-color: #82cfff;
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
  margin: 0;
  margin-right: 1vw;
`;

const Notice = styled.div`
  width: 100%;
  text-align: center;
  font-size: 14px;
`;
