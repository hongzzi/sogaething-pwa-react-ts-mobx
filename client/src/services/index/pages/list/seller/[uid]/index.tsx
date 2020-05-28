import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../../../components/CategoryHeader';
import Nav from '../../../../components/Nav';
import PostList from '../../../../components/PostList';

export interface IPostListSellerProps {
}

export default (props: IPostListSellerProps) => {
    return (
        <Layout>
            <Categoryheader type={'normal'} text={'판매내역'}/>
            <PostList />
            <Nav />
        </Layout>
    )
}

const Layout = styled.div`
  position: relative;
  padding-bottom: 48px;
`;
