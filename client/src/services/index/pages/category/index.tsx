import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../components/CategoryHeader';
import Nav from '../../components/Nav';

export interface ICategoryProps {
}

export default (props: ICategoryProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'chat'} text={'카테고리'}/>
            <Nav />
        </Wrapper>
    )
}

const Wrapper = styled.div`
    /* display: grid; */
    /* grid-auto-rows: 56px auto; */
    height: 100%;
    /* grid-template-areas:
    "CH"
    "CC"; */
`;
