import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../../components/CategoryHeader';
import SellingForm from '../../../components/SellingForm';

export interface ISellingFormPageProps {
}

export default (props: ISellingFormPageProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'normal'} text={'판매글 작성하기'}/>
            <SellingForm />
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
