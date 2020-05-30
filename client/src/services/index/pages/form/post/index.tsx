import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../../components/CategoryHeader';
import PostForm from '../../../components/SellingForm';

export interface IPostFormPageProps {
}

export default (props: IPostFormPageProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'normal'} text={'판매글 작성하기'}/>
            <PostForm />
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
    ;
`;
