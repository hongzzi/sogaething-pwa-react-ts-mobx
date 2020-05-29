import React, { useState, useEffect } from 'react';
import styled from '~/styled';
import useStores from '../../../helpers/useStores';

import { useRouter } from 'next/router';
import { useGetPostQuery } from '~/generated/graphql';

import ImageSlider from '../../../components/ImageSlider';
import PostDetail from '../../../components/PostDetail';
import PostDetailNav from '../../../components/PostDetailNav';

export interface IPost {
    dealId: string,
    postId: number,
    imgPaths: string[],
    title: string,
    category: string,
    hashtag: string[],
    contents: string,
    price: number,
    buyerId: number,
    sellerId: number,
    user: IUser,
}

export interface IUser {
    name: string,
    address: string | null,
    trust: number,
    numOfPosts: number,
    imgurl: string,
}

export default function Detail(props: any) {
    const store = useStores()
    const router = useRouter()
    const { pid } = router.query
    const { data, loading, error } = useGetPostQuery({ variables: { postId: pid } });

    useEffect(() => {
       if ( data ) {
        //    store.postStore.setDeal(data.findDetailDealByPost);
        // console.log(data.findDetailDealByPost);
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
                data && data.findDetailDealByPost && (
                    <Layout>
                        <ImageSlider images={data.findDetailDealByPost.imgPaths as string[]} />
                        <PostDetail data={data.findDetailDealByPost as IPost} loading={loading} />
                        <PostDetailNav data={data.findDetailDealByPost as IPost} loading={loading} />
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
