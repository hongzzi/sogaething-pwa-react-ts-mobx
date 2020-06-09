import { useRouter } from 'next/router';
import * as React from 'react';
import CategoryCardList from '~/services/index/components/CategoryCardList';
import CategoryHeader from '~/services/index/components/CategoryHeader';
import styled from '~/styled';
import { categoryItems } from '..';

export default () => {
    const router = useRouter();

    return (
        <Wrapper>
            <CategoryHeader type={'normal'} text={categoryItems[router.query.cName]} />
            <CategoryCardList categoryId={router.query.cName}/>
        </Wrapper>
    )
}

const Wrapper = styled.div`

`;
