import Link from 'next/link';
import * as React from 'react';
import styled, { keyframes } from '~/styled';
import { IRecentPost } from '.';
import { numberWithCommas } from '../../helpers/comma';
import ImageView from '../ImageView';
import { TextLoader } from '../LoaderPlaceholder';

interface IProductCard {
  cardData: any | null;
  loading?: boolean;
  idx: number;
}

export default (props: IProductCard) => {
  let hashtags;
  let postId = '';

  if (props.cardData !== null) {
    hashtags = props.cardData.hashTags.map(
      (item: any) => '#' + item.hashtag + ' ',
    );
    postId = props.cardData.postId;
  }

  return (

    <Link href={`/post/${postId}`}>
    <Wrapper idx={props.idx} loading={props.loading}>

      <ImageView
        src={
          props.loading
            ? 'https://www.sctech.edu/wp-content/plugins/ajax-search-pro/img/default.jpg'
            : props.cardData!.imgUrls.length === 0
            ? 'https://www.sctech.edu/wp-content/plugins/ajax-search-pro/img/default.jpg'
            : props.cardData!.imgUrls[0].imgPath
        }
      />
      <TextCardContainer>
        <Line color='black' size={12}>
          <b>{!props.loading && hashtags}</b>
        </Line>
        <Line color={'#868e96'} size={10}>
          {props.cardData && props.cardData!.category}
        </Line>
        <Line color={'#ffaa00'}>
          <b>
            {props.cardData && numberWithCommas(props.cardData!.price) + '원'}
          </b>
        </Line>
      </TextCardContainer>
    </Wrapper>
    </Link >
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
  max-height: 55vw;
  border-radius: 8px;
  overflow: hidden;
  display: block;
  float: ${(props) => (props.idx % 2 === 1 ? 'left' : 'right')};
  margin-bottom: 15px;
  background-color: ${(props) => (props.loading ? 'lightgray' : '')};
  animation: ${boxFade} ${(props) => props.loading ? '1.5s infinite ease-in-out' : ''};
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
