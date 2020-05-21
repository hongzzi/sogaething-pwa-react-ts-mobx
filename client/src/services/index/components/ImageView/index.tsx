import * as React from 'react';
import styled from '~/styled';

interface IImageView {
    src: string
}

export default (props: IImageView) => {
    return <Wrapper src={props.src} />
}

const Wrapper = styled.img`
    width: 100%;
    height: 30vw;
    object-fit: cover;
`;
