import * as React from 'react';
import styled from '~/styled';
import Nav from '../../../../components/Nav';

export interface IPostListSellerProps {
}

export default (props: IPostListSellerProps) => {
    return (
        <Layout>
            <Nav />
        </Layout>
    )
}

const Layout = styled.div`
  position: relative;
  padding-bottom: 48px;
`;
