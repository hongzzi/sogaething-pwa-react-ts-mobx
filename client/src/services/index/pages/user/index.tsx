import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../components/CategoryHeader';
import Nav from '../../components/Nav';

export interface IUserProps {
}

export default (props: IUserProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'chat'} text={'내 정보'}/>
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
