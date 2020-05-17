import * as React from 'react';
import styled from 'styled-components';

export interface ICircleImageViewProps {
    src : string;
    size : number;
}

export default class CircleImageView extends React.Component<ICircleImageViewProps> {
    
  public render() {
    const {src, size} = this.props;
    return (
      <Wrapper src={src} size={size} />
    );
  }
}


const Wrapper = styled.img`
    border-radius: 50%;
    height : ${(props : {size : number}) => props.size * 20 + 'px'};
    width : ${(props : {size : number}) => props.size * 20 + 'px'};
`;
