import moment from 'moment';
import Link from 'next/link';
import * as React from 'react';
import { IPostMetaOutput } from '~/generated/graphql';
import styled, { keyframes } from '~/styled';
import { numberWithCommas } from '../../helpers/comma';
import ImageView from '../ImageView';
import { TextLoader } from '../LoaderPlaceholder';

interface IProductCard {
  cardData: IPostMetaOutput | null;
  loading?: boolean;
  idx: number;
}

export default (props: IProductCard) => {
  let postId = -1;

  if (props.cardData) {
    postId = props.cardData.postId!;
  }
  return (
    <Link href={`/post/${postId}`}>
      <Wrapper idx={props.idx} loading={props.loading}>
        <ImageView
          src={
            props.loading
              ? 'https://www.sctech.edu/wp-content/plugins/ajax-search-pro/img/default.jpg'
              : props.cardData!.imgPath! ?
              props.cardData!.imgPath!
              : 'https://www.sctech.edu/wp-content/plugins/ajax-search-pro/img/default.jpg'
          }
        />
        <TextCardContainer>
          <Line color='black' size={12}>
            <b>{!props.loading && props.cardData!.hashtag!.map((item) => '#' + item + ' ')}</b>
          </Line>
          <Line color={'#868e96'} size={10}>
            {props.cardData && props.cardData!.category}
          </Line>
          <Line color={'#ffaa00'}>
            <b>
              {props.cardData && numberWithCommas(props.cardData.price!) + '원'}
            </b>
          </Line>
          <Line color={'#868e96'} size={10}>
            {props.cardData && moment(props.cardData.createdDate).fromNow()}
          </Line>
        </TextCardContainer>
      </Wrapper>
    </Link>
  );
};

const boxFade = keyframes`
  0% {
    opacity: 1;
  }
  50% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
`;

const Wrapper = styled.div<Pick<IProductCard, 'idx' | 'loading'>>`
  border: 1px solid lightgray;
  width: 44vw;
  min-height: 52vw;
  max-height: 58vw;
  border-radius: 8px;
  overflow: hidden;
  display: block;
  float: ${(props) => (props.idx % 2 === 0 ? 'left' : 'right')};
  margin-bottom: 15px;
  background-color: ${(props) => (props.loading ? 'lightgray' : '')};
  animation: ${boxFade} ${(props: any) => (props.loading ? '1.5s infinite ease-in-out' : '')};
`;

const TextCardContainer = styled.div`
  text-align: left;
  line-height: 20px;
  padding: 1vw 4vw 4vw 4vw;
`;

interface ILineProps {
  color: string;
  size?: number;
}

const Line = styled.p<ILineProps>`
  color: ${(props) => props.color};
  font-size: ${(props) => props.size + 'px'};
  margin: 0;
  width: 100%;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
`;
