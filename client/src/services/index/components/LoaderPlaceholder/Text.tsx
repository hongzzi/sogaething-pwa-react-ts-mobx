import * as React from 'react';
import styled, { keyframes } from '~/styled';

interface ITextLoader {
    size: {
        height: number,
        width: number,
    }
}

export const TextLoader = (props: ITextLoader) => {
  return  <Wrapper size={props.size}/>;
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
`

const Wrapper = styled.div<ITextLoader>`
  display: inline-block;
  height: ${props=> props.size.height + 'px'};;
  width: ${props=> props.size.width + 'px'};
  background-color: lightgray;
  animation: ${boxFade} 1.5s infinite ease-in-out;
`;
