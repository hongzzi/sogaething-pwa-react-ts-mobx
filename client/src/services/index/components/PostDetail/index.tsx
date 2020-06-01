import * as React from 'react';
import styled from '~/styled';

import PostDetailContentCard from '../PostDetailContentCard';
import PostDetailUserCard from '../PostDetailUserCard';

export interface IPostDetailProps {
    data: any,
    loading: boolean,
}

export default (props: IPostDetailProps) => {
    const { data, loading } = props;
    return (
        <Wrapper>
            <PostDetailUserCard user={data.user} />
            <PostDetailContentCard data={data} loading={loading} />
        </Wrapper>
    );
}

const Wrapper = styled.div`
    position: relative;
    width: 100%;
    min-height: auto;
`
