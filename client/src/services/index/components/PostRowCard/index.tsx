import * as React from 'react';
import styled from '~/styled';

import CustomIcont from '../CustomIcon'

import clockIcon from '../../assets/img/clock.png';
import heartIcon from '../../assets/img/heart.png';
import moreIcon from '../../assets/img/moreVertical.png';
import TestImg from '../../assets/img/Rectangle.png';

export interface IPostRowCardProps {
}

export default function PostRowCard(props: IPostRowCardProps) {
    return (
        <Wrapper>
            <ProductImg src={TestImg} />
            <ProductInfo>
                <TitleText>맥북 13인치 팝니다.</TitleText>
                <PriceText>200,000 원</PriceText>
                <StatusBar>
                    <SmallIcon src={heartIcon} />
                    <SpanStyle> 4 </SpanStyle>
                    <SmallIcon src={clockIcon} />
                    <SpanStyle> 23 시간 전 </SpanStyle>
                </StatusBar>
            </ProductInfo>
            <ProductSettingArea>
                <CustomIcont url={moreIcon} />
            </ProductSettingArea>
        </Wrapper>
    );
}

const Wrapper = styled.div`
    position: relative;
    width: 100%;
    height: auto;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 0.6rem 1.2rem;
`

const ProductImg = styled.img`
    width: 6rem;
    height: 6rem;
    border-radius: 5px;
`

const ProductInfo = styled.div`
    flex-basis: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 0.1rem 1rem;
    width: auto;
`

const TitleText = styled.div`
    color: #888;
    font-size: 16px;
`

const PriceText = styled.div`
    flex-basis: 100%;
    color: black;
    font-size: 20px;
    font-weight: bold;
`

const StatusBar = styled.div`
    font-size: 12px;
`

const ProductSettingArea = styled.div`
    display: inline;
`

const SmallIcon = styled.img`
    width: 12px;
    height: 12px;
`

const SpanStyle = styled.span`
    padding-right: 0.2rem;
    vertical-align: top;
`
