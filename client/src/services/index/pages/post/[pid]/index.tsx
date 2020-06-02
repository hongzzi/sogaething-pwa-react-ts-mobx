import React, { useState, useEffect } from 'react';
import styled from '~/styled';

import Link from 'next/link';
import { useRouter } from 'next/router';
import { useGetPostQuery, useCreateHistoryMutation } from '~/generated/graphql';

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
    name: string,
    address: string | null,
    trust: number,
    numOfPosts: number,
    imgurl: string,
}

export default function Detail(props: any) {
    const router = useRouter()
    const { pid } = router.query
    const { data, loading, error } = useGetPostQuery({ variables: { postId: pid } });
    const mutation = useCreateHistoryMutation();

    useEffect(() => {
        if (data && data.findByDetailPost) {
            // 히스토리 추가, 뷰카운트 업데이트
            mutation({
                variables: {
                    input: pid,
                },
            })
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
                        <CategoryHeader type={'normal'} text={' '} />
                        <ContentBody>
                            <ImageSlider images={data.findByDetailPost.imgPaths as string[]} />
                            <PostDetail data={data.findByDetailPost} loading={loading} />
                            <PostDetailNav data={data.findByDetailPost} loading={loading} />
                        </ContentBody>
                    </Layout>
                )
            }
        </>
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
