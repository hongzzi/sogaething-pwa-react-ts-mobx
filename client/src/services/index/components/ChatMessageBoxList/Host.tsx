import moment from 'moment';
import { useRouter } from 'next/router';
import * as React from 'react';
import styled from '~/styled';
import NoAvatar from '../../assets/img/no-avatar.png?url';
import useStores from '../../helpers/useStores';
import { IDepositRequestDto } from '../../service/BankService';
import { IChatDto } from '../../service/ChatService';
import CircleImageView from '../CircleImageView';
import CommonBtn from '../CommonBtn';

interface IChatMessageBox {
  cardData: IChatDto;
  imgPath: string;
}

export default (props: IChatMessageBox) => {
  const { sender, createdDateTime, message, type } = props.cardData;
  const {chatStore} = useStores();
  const mnt = moment(createdDateTime);
  const router = useRouter();
  let tossDeposit: Pick<IDepositRequestDto, 'message' | 'bankName' | 'bankAccountNo' | 'amount'>;
  if (type === 'REMIT' && typeof message === 'string') {
    tossDeposit = JSON.parse(message) as Pick<IDepositRequestDto, 'message' | 'bankName' | 'bankAccountNo' | 'amount'>;
  }

  const handleDepositClick = () => {
    chatStore.postDepostService({...tossDeposit, apiKey: ''})
    .then((res) => {
      router.push(res.data.success.link);
    })
    .catch((e) => {
      console.log(e);
    })
  };
  return (
    <Wrapper>
      {type === 'TALK' && (
        <>
          <CircleImageView
            size={1.8}
            src={props.imgPath ? props.imgPath : NoAvatar}
          />
          <Message>{message}</Message>
          <WrapperTime>
            <Time>{mnt.fromNow()}</Time>
          </WrapperTime>
        </>
      )}
      {type === 'REMIT' &&
        <Notice>
          <Message color={'#f0f0ff'} size={'80%'}>
            <h2>{'입금 요청'}</h2>
          {tossDeposit!.bankName}은행 {tossDeposit!.bankAccountNo} <br />
          <h3>{tossDeposit!.amount}원</h3>
          <div onClick={handleDepositClick}>
            <CommonBtn text={'입금하기'} type={'primary'} />
          </div>
          </Message>
        </Notice>
      }
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  display: flex;
  margin: 2vw;
  min-height: 10px;
  max-height: 300px;
  & > img {
    margin-right: 2vw;
  }
`;

const Message = styled.div<{color?: string, size?: string}>`
  font-size: 12px;
  min-height: 10px;
  max-height: 300px;
  min-width: ${(props) => props.size === undefined ? '30px' : props.size };
  max-width: 60vw;

  padding: 8px;

  background-color: ${(props) => props.color === undefined ? '#82cfff' : props.color};
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
  width: 100%;
  display: flex;
  align-content: center;
  justify-content: center;
  text-align: center;
  font-size: 14px;
`;
