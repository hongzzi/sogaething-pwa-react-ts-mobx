import React, { useState, useEffect } from 'react';
import styled from '~/styled';
import useStores from '../../../helpers/useStores';

import { useRouter } from 'next/router';
import { useGetPostQuery } from '~/generated/graphql';

import ImageSlider from '../../../components/ImageSlider';
import PostDetail from '../../../components/PostDetail';
import PostDetailNav from '../../../components/PostDetailNav';

export interface IPost {
    postId: number,
    title: string,
    category: string,
    imgPaths: string[],
    hashtag: string[],
    contents: string,
    price: number,
    user: IUser,
    createdDate: string,
    modifiedDate: string,
}

export interface IUser {
    name: string,
    address: string | null,
    trust: number,
    numOfPosts: number,
    imgurl: string,
}

export default function Detail(props: any) {
    // const store = useStores()
    const router = useRouter()
    const { pid } = router.query
    const { data, loading, error } = useGetPostQuery({ variables: { postId: pid } });

    useEffect(() => {
       if ( data ) {
        //    store.postStore.setDeal(data.findDetailDealByPost);
        console.log(data.findByDetailPost);
       }
    }, [data])

    return (
        <>
            {
                loading &&
                <p>loading…</p>
            }
            {
                error &&
                <p>error</p>
            }
            {
                data && data.findByDetailPost && (
                    <Layout>
                        <ImageSlider images={data.findByDetailPost.imgPaths as string[]} />
                        <PostDetail data={data.findByDetailPost} loading={loading} />
                        <PostDetailNav data={data.findByDetailPost} loading={loading} />
                    </Layout>
                )
            }
        </>
    );
}

const Layout = styled.div`
    font-family: NotoSansCJKkr;
    position: relative;
`

const StatusHeader = styled.div`
    width: 100%;
    height: 24px;
`;
