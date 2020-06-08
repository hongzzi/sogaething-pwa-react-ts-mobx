import { useRouter } from 'next/router';
import * as React from 'react';
import CategoryHeader from '~/services/index/components/CategoryHeader';
import styled from '~/styled';
import { categoryItems } from '..';
import CategoryCardList from '~/services/index/components/CategoryCardList';

export default () => {
    const router = useRouter();

    return (
        <Wrapper>
            <CategoryHeader type={'normal'} text={categoryItems[router.query.cName]} />
            <CategoryCardList />
        </Wrapper>
    )
}

const Wrapper = styled.div`

`;
