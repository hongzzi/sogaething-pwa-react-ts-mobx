import * as React from 'react';
import styled from '~/styled';

import Carousel from 'nuka-carousel';
import RectangleImageView from '../RectangleImageView';

import PrevIcon from '../../assets/img/arrow-ios-left.png?url';
import RightIcon from '../../assets/img/arrow-ios-right.png?url';

export interface IImageSliderProps {
    images?: IImg[] | null,
}

export interface IImg {
    imgPath?: string;
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
                        <button onClick={previousSlide}><BtnIcon src={PrevIcon} /></button>
                    )}
                    // tslint:disable-next-line:jsx-no-lambda
                    renderCenterRightControls={({ nextSlide }) => (
                        <button onClick={nextSlide}><BtnIcon src={RightIcon} /></button>
                    )}
                >
                    {images && images.map((url: any, index: number) => (
                        <RectangleImageView key={index} src={url.imgPath} />
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
