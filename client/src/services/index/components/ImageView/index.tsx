import * as React from 'react';
import styled from '~/styled';

interface IImageView {
    src: string
    size?: number;
}

export default (props: IImageView) => {
    return <Wrapper src={props.src} />
}

const Wrapper = styled.img <Pick<IImageView, 'size'>>`
    width: 100%;
    height: ${props => props.size !== null ? props.size : '30vw'};
    object-fit: cover;
`;
