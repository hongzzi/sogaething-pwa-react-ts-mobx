import * as React from 'react';
import styled from 'styled-components';

export interface ICircleImageViewProps {
    src: string;
    size: number;
    radius?: number;
    edit?: boolean;
}

export default class CircleImageView extends React.Component<ICircleImageViewProps> {

  public render() {
    const {src, size, radius, edit} = this.props;
    return (
      <Wrapper src={src} size={size} radius={radius} edit={edit} />
    );
  }
}

const Wrapper = styled.img<Pick<ICircleImageViewProps, 'size'|'radius'|'edit'>>`
    border-radius: ${(props) => props.radius !== undefined ? props.radius + 'px' : '50%'};
    height : ${(props: {size: number}) => props.size * 20 + 'px'};
    width : ${(props: {size: number}) => props.size * 20 + 'px'};
    background-color: white;
`;
