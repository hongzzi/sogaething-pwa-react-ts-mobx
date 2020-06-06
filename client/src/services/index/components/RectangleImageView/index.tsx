import * as React from 'react';
import styled from 'styled-components';

export interface IRectangleImageViewProps {
    src: string;
}

export default class RectangleImageView extends React.Component<IRectangleImageViewProps> {
    public render() {
        const { src } = this.props;
        return (
            <Wrapper src={src} />
        );
    }
}

const Wrapper = styled.img`
    position: absolute;
    min-width: 360px;
    min-height: 360px;
    max-width: 600px;
    max-height: 600px;
    width: 100vw;
    height: 100vw;
    object-fit: cover;
`
