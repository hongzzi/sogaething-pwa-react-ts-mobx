import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../../../components/CategoryHeader';
import Nav from '../../../../components/Nav';
import PostList from '../../../../components/PostList';

export interface IPostListSellerProps {
}

export default (props: IPostListSellerProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'normal'} text={'판매내역'}/>
            <PostList />
            <Nav />
        </Wrapper>
    )
}

const Wrapper = styled.div`
    display: grid;
    grid-auto-rows: 56px minmax(75vh, 85vh) 60px;
    height: 100%;
    grid-template-areas:
    "CH"
    "CC"
    "CI"
    ;
`;
