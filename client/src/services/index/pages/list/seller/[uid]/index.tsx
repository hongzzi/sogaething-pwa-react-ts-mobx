import React, { useEffect } from 'react';
import styled from '~/styled';

import { useRouter } from 'next/router';
import { useGetMyPostsQuery } from '~/generated/graphql';

import Loader from '~/services/index/components/Loader';
import Categoryheader from '../../../../components/CategoryHeader';
import Nav from '../../../../components/Nav';
import PostList from '../../../../components/PostList';

export interface IPostListSellerProps {
}

export interface IMetaData {
    postId: number,
    title: string,
    category: string,
    imgPath: string,
    price: number,
    hashtag: string[],
    createdDate: string,
    modifiedDate: string,
}

export default (props: IPostListSellerProps) => {
    const router = useRouter()
    const { uid } = router.query
    const { data, loading, error } = useGetMyPostsQuery({ variables: { userId: uid } });

    return (
        <Wrapper>
            <Categoryheader type={'normal'} text={'판매내역'} />
            {
                loading &&
                <Loader />
            }
            {
                error &&
                <p>error</p>
            }
            {data && data.findPostListByUserId &&
                <PostList data={data.findPostListByUserId as IMetaData[]} />
            }
            {/* <Nav /> */}
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
