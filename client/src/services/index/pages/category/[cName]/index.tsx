import { useRouter } from 'next/router';
import * as React from 'react';
import styled from '~/styled';
import CategoryHeader from '~/services/index/components/CategoryHeader';

export default () => {
    const router = useRouter();

    console.log(router.query.cName);
    return (
        <Wrapper>
            <CategoryHeader type={'normal'} text={router.query.cName} />
        </Wrapper>
    )
}

const Wrapper = styled.div`

`;
