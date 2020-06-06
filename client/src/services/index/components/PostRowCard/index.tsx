import * as React from 'react';
import styled from '~/styled';

import moment from 'moment';

import Link from 'next/link';
import { numberWithCommas } from '../../helpers/comma';
import { IMetaData } from '../../pages/list/seller/[uid]/index';
import CustomIcont from '../CustomIcon'

import clockIcon from '../../assets/img/list-clock.png';
import heartIcon from '../../assets/img/list-like.png';
import moreIcon from '../../assets/img/moreVertical.png';

export interface IPostRowCardProps {
    data: IMetaData;
}

export default function PostRowCard(props: IPostRowCardProps) {
    const { data } = props;
    const mnt = moment(data.modifiedDate);
    const diffTime = moment.duration(mnt.diff(moment())).asHours();

    return (
        <Wrapper>
            <ProductImg src={data.imgPath} />
            <Link key={data.postId} href='/post/[pid]' as={`/post/${data.postId}`} >
                <ProductInfo>
                    <TitleText>{data.title}</TitleText>
                    <PriceText>{numberWithCommas(data.price)} 원</PriceText>
                    <StatusBar>
                        <SmallIcon src={heartIcon} />
                        <SpanStyle> 4 </SpanStyle>
                        <SmallIcon src={clockIcon} />
                        <SpanStyle> {diffTime >= -168 ? mnt.fromNow() : mnt.format('YY년 M월 D일')} </SpanStyle>
                    </StatusBar>
                </ProductInfo>
            </Link>
            <ProductSettingArea>
                <CustomIcont url={moreIcon} />
            </ProductSettingArea>
        </Wrapper>
    );
}

const LinkWrapper = styled.a`
    position: relative;
`

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
    object-fit: cover;
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
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 45vw;
    height: 2.2rem;
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
