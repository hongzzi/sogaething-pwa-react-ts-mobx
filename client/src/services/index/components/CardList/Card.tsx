import * as React from 'react';
import styled from '~/styled';
import {IFindUserHistoryByUserId} from '.';
import { numberWithCommas } from '../../helpers/comma';
import { TextLoader } from '../LoaderPlaceholder';
interface ICardProps {
  cardData: IFindUserHistoryByUserId | null;
  loading: boolean;
}

export default (props: ICardProps) => {
  let bgImg = 'https://www.sctech.edu/wp-content/plugins/ajax-search-pro/img/default.jpg';
  let hashtags;
  let price = 0;
  if (props.cardData !== null) {
    hashtags = props.cardData.hashTags.map(
      (item: any) => '#' + item.hashtag + ' ',
    );
    bgImg = props.cardData.imgUrls[0].imgPath;
    price = props.cardData.price;
  }
  return (
    <Card bgImg={bgImg}>
      <WrapperText>
          {props.loading && <TextLoader size={{height: 10, width: 50}} />}
          {!props.loading && hashtags} <br/>
          {props.loading && <TextLoader size={{height: 10, width: 80}} />}
          {!props.loading && numberWithCommas(price)+'원'}
      </WrapperText>
    </Card>
  );
};

interface ICard {
  bgImg: string;
}

export const Card = styled.div<ICard>`
  display: inline-block;
  padding: 5px;
  margin-right: 8px;
  width: 87px;
  height: 87px;
  border-radius: 5px;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0), gray),
    url(${(props) => props.bgImg});
  background-size: cover;
  background-repeat: no-repeat;
  background-position: 50% 50%;
`;

const WrapperText = styled.div`
  margin-top: 60%;
  font-size: 10px;
  color: white;
  font-weight: bold;
`;
