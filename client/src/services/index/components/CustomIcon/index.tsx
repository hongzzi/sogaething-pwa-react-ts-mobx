import * as React from 'react';
import styled from '~/styled';

interface IIconProps {
  url: string;
}

export default (props: IIconProps) => {
  return (
    <Wrapper>
      <Icon src={props.url} />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 48px;
  height: 48px;
  text-align: center;
  display: grid;
  grid-area:
    ". . ."
    ". img ."
    ". . ."
  ;
`;

export const Icon = styled.img`
  width: 24px;
  height: 24px;
  margin: 0;
  grid-area: img;
`;
