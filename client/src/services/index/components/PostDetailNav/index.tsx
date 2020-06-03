import * as React from 'react';
import styled from '~/styled';

import { numberWithCommas } from '../../helpers/comma';
import CommonBtn from '../CommonBtn';
import CustomIcon from '../CustomIcon';

import { useRouter } from 'next/router';
import HeartIcon from '../../assets/img/detail-like.png'
import useStores from '../../helpers/useStores';
import { ICreateChatRoomRequestDto } from '../../service/ChatService';

export interface IPostDetailNavProps {
  loading: boolean,
  data: any,
}

export default function PostDetailNav(props: IPostDetailNavProps) {
  const { loading, data } = props;
  const router = useRouter();
  const store = useStores();
  const handleChatClick = () => {
    const createChatData: ICreateChatRoomRequestDto = {
      buyerId: store.authStore.getAuth()!.userId + '',
      sellerId: data.user.userId,
      postId: data.postId,
    }

    store.chatStore.postCreateChatRoom(createChatData).then((res) => {
      router.push(`/chat/${res.data.content}`);
    });
  }
  return (
    <>
      {
        !loading && data &&
        <Wrapper>
          <FlexBox>
            <IconBorder>
              <CustomIcon url={HeartIcon} />
            </IconBorder>
            <PriceTextLine>{numberWithCommas(data.price)} 원</PriceTextLine>
            <div onClick={handleChatClick}>
              <CommonBtn type={'chatting'} text={'연락하기'} />
            </div>
          </FlexBox>
        </Wrapper>
      }
    </>
  );
}

const IconBorder = styled.button`
    border-radius: 50%;
    border: solid 1px ${(props) => props.theme.button.chatting.bodyColor};;
    background: #fff 0;
    padding: 0;
`

const Wrapper = styled.div`
    position: fixed;
    background: #fff;
    bottom: 0;
    height: 4rem;
    width : 100%;
    border-top: solid 1px #ccc;
`

const FlexBox = styled.div`
    height: 100%;
    display: flex;
    flex-direction: row;
    flex-basis: auto;
    justify-content: space-between;
    align-content: stretch;
    align-items: center;
    margin: 0 0.5rem;
`

const PriceTextLine = styled.span`
    flex-basis: 45%;
    font-size: 1.3rem;
    font-weight: bold;
    text-align: right;
`
