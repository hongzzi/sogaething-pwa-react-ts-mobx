import * as React from 'react';
import styled from 'styled-components';
import { keyframes } from '~/styled';

export interface ICirclePlaceHolderProps {
    size : number;
}

export default class CirclePlaceHolder extends React.Component<ICirclePlaceHolderProps> {
  public render() {
    const {size} = this.props;
    return (
      <Wrapper size={size} />
    );
  }
}

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

const Wrapper = styled.img`
    border-radius: 50%;  
    height : ${(props : {size : number}) => props.size * 20 + 'px'};
    width : ${(props : {size : number}) => props.size * 20 + 'px'};
    background-color: lightgray;
    animation: ${boxFade} 1.5s infinite ease-in-out;
`;
