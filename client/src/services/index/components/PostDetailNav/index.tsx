import * as React from 'react';
import styled from '~/styled';

import { numberWithCommas } from '../../helpers/comma';
import CommonBtn from '../CommonBtn';
import CustomIcon from '../CustomIcon';

import HeartIcon from '../../assets/img/heart-fill1.png?url'
import useStores from '../../helpers/useStores';
import { IPost } from '../../pages/post/[pid]/index';

export interface IPostDetailNavProps {
  loading: boolean,
  data: any,
}

export default function PostDetailNav(props: IPostDetailNavProps) {
  const { loading, data } = props;
  const store = useStores();
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
            <CommonBtn type={'chatting'} text={'연락하기'} />
          </FlexBox>
        </Wrapper>
      }
    </>
  );
}

const IconBorder = styled.button`
    border-radius: 50%;
    border: solid 1px ${(props) => props.theme.button['chatting'].bodyColor};;
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
