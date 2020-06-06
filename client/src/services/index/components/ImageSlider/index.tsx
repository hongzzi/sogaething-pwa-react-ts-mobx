import * as React from 'react';
import styled from '~/styled';

import Carousel from 'nuka-carousel';
import RectangleImageView from '../RectangleImageView';

import NextIcon from '../../assets/img/detail-imgnext.png?url';
import PrevIcon from '../../assets/img/detail-imgprev.png?url';

export interface IImageSliderProps {
    images?: string[] | null,
}

export default class ImageSlider extends React.Component<IImageSliderProps> {
    public render() {
        const { images } = this.props;
        return (
            <ImageContainer>
                <Carousel
                    width='100%'
                    height='auto'
                    // tslint:disable-next-line:jsx-no-lambda
                    renderCenterLeftControls={({ previousSlide }) => (
                        <Btn onClick={previousSlide}><BtnIcon src={PrevIcon} /></Btn>
                    )}
                    // tslint:disable-next-line:jsx-no-lambda
                    renderCenterRightControls={({ nextSlide }) => (
                        <Btn onClick={nextSlide}><BtnIcon src={NextIcon} /></Btn>
                    )}
                >
                    {images && images.map((url: string, index: number) => (
                        <RectangleImageView key={index} src={url} />
                    ))}
                </Carousel>
            </ImageContainer>
        );
    }
}

const ImageContainer = styled.div`
    position: relative;
    width: 100%;
    height: auto;
    max-width: 36rem;
    max-height: 32rem;
    overflow: hidden;
`

const BtnIcon = styled.img`
    width: 24px;
    height: 24px;
`

const Btn = styled.button`
    background-color:transparent;
    border:0px transparent solid;
`
