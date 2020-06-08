import React, { useEffect, useState } from 'react';
import useStores from '~/services/index/helpers/useStores';
import styled from '~/styled';

import Link from 'next/link';
import { useRouter } from 'next/router';
import { useCreateHistoryMutation, useGetPostQuery, useUpdateViewMutation } from '~/generated/graphql';

import Loader from '~/services/index/components/Loader';
import CategoryHeader from '../../../components/CategoryHeader';
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
    viewCount: number,
    isBuy: boolean,
    deal: string,
    dealState: string,
    saleDate: string,
    transaction: string,
    createdDate: string,
    modifiedDate: string,
}

export interface IUser {
    userId: number,
    name: string,
    address: string | null,
    trust: number,
    numOfPosts: number,
    imgurl: string,
}

export default function Detail(props: any) {
    const store = useStores();
    const router = useRouter()
    const { pid } = router.query
    const updateMutaion = useUpdateViewMutation({ variables: { postId: pid } });
    const { data, loading, error } = useGetPostQuery({ variables: { postId: pid } });
    const createMutation = useCreateHistoryMutation();

    useEffect(() => {
        if (data && data.findByDetailPost) {
            // 히스토리 추가, 뷰카운트 업데이트
            if (data.findByDetailPost.user && data.findByDetailPost.user.userId) {
                if (store.authStore.auth && store.authStore.auth.userId) {
                    const suid = data.findByDetailPost.user.userId;
                    const buid = store.authStore.auth.userId;
                    if (suid !== buid) {
                        createMutation({
                            variables: {
                                input: pid,
                            },
                        }).then((res) => {
                            // console.log(res);
                        }).catch((error) => {
                            // console.log(error);
                        })
                    }
                }
            }
        }
    }, [data])

    return (
        <Layout>
            <CategoryHeader type={'normal'} text={' '} />
            <ContentBody>
                {
                    loading &&
                    <Loader />
                }
                {
                    error &&
                    <p>error</p>
                }
                {
                    data && data.findByDetailPost && (
                        <>
                            <ImageSlider images={data.findByDetailPost.imgPaths as string[]} />
                            <PostDetail data={data.findByDetailPost} loading={loading} />
                            <PostDetailNav data={data.findByDetailPost} loading={loading} />
                        </>
                    )
                }
            </ContentBody>
        </Layout>
    );
}

const Layout = styled.div`
    font-family: NotoSansCJKkr;
    position: relative;
    display: grid;
    grid-auto-rows: 56px auto;
    height: 100%;
    grid-template-areas:
    "CH"
    "CC"
    ;
`

const ContentBody = styled.div`
    grid-area: CC;
    height: auto;
`
