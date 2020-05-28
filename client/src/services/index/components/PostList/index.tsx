import * as React from 'react';
import styled from '~/styled'

import PostRowCard from '../PostRowCard';

export interface IPostListProps {
}


export default function PostList(props: IPostListProps) {
    const item = [1, 2, 3, 4];
    return (
        <Wrapper>
            <CardContainer>
                <PostCount>총 <SpanStyle>{item.length}</SpanStyle> 개의 상품</PostCount>
                {item.map((index) => (
                    <PostRowCard key={index} />
                ))}
            </CardContainer>
        </Wrapper>
    );
}

const Wrapper = styled.div`
    position: relative;
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
