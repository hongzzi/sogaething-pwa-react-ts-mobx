import * as React from 'react';
import styled from '~/styled'

import { IMetaData } from '../../pages/list/seller/[uid]/index';
import PostRowCard from '../PostRowCard';

export interface IPostListProps {
    data: IMetaData[],
}

export default function PostList(props: IPostListProps) {
    const { data } = props;
    return (
        <Wrapper>
            <CardContainer>
                <PostCount>총 <SpanStyle>{data.length}</SpanStyle> 개의 상품</PostCount>
                {data.map((item, index) => (
                    <PostRowCard data={item} key={index} />
                ))}
            </CardContainer>
        </Wrapper>
    );
}

const Wrapper = styled.div`
    position: relative;
    width: 100vw;
    grid-area: CC;
    padding-bottom: 4rem;
`

const PostCount = styled.div`
    padding: 1.2rem 1.2rem 0.6rem 1.2rem;
    color: #aaa;
`

const SpanStyle = styled.span`
    font-weight: bold;
    color: red;
`

const CardContainer = styled.div`
    width: 100%;
    height: auto;
`
