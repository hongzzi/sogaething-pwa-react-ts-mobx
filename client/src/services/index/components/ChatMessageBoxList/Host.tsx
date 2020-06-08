import moment from 'moment';
import * as React from 'react';
import styled from '~/styled';
import { IChatDto } from '../../service/ChatService';
import CircleImageView from '../CircleImageView';
import NoAvatar from '../../assets/img/no-avatar.png?url';
interface IChatMessageBox {
  cardData: IChatDto;
  imgPath: string;
}

export default (props: IChatMessageBox) => {
  const { sender, createdDateTime, message, type } = props.cardData;
  const mnt = moment(createdDateTime);
  return (
    <Wrapper>
      {type !== 'ENTER' && (
        <CircleImageView
          size={1.8}
          src={props.imgPath ? props.imgPath : NoAvatar}
        />
      )}
      {type === 'ENTER' ? (
        <Notice>{message}</Notice>
      ) : (
        <Message>{message}</Message>
      )}
      <WrapperTime>
        {type !== 'ENTER' && <Time>{mnt.fromNow()}</Time>}
      </WrapperTime>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  display: flex;
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

  background-color: #fff;

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
  margin-left: 1vw;
`;

const Notice = styled.div`
  text-align: center;
  font-size: 14px;
`;
