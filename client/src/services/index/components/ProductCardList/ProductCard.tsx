import * as React from 'react';
import styled from '~/styled';
import ImageView from '../ImageView';

interface IProductCard {
  cardData: {
    title: string;
    price: string;
    place: string;
    imageSrc: string;
  };
  idx: number;
}

export default (props: IProductCard) => {
  return (
    <Wrapper idx={props.idx}>
      <ImageView src={props.cardData.imageSrc} />
      <TextCardContainer>
        <Line color='black' size={12}>
          <b>
            {props.cardData.title} #macbook #macbook #macbook #macbook #macbook
          </b>
        </Line>
        <Line color={'#868e96'} size={10}>
          {props.cardData.place}
        </Line>
        <Line color={'#ffaa00'}>
          <b>{props.cardData.price}원</b>
        </Line>
      </TextCardContainer>
    </Wrapper>
  );
};

const Wrapper = styled.div<Pick<IProductCard, 'idx'>>`
  border: 1px solid lightgray;
  width: 44vw;
  border-radius: 8px;
  overflow: hidden;
  display: block;
  float: ${(props) => (props.idx % 2 === 1 ? 'left' : 'right')};
  margin-bottom: 15px;
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
