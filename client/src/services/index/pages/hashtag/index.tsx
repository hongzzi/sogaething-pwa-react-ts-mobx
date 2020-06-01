import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../components/CategoryHeader';
import HashTagSearch from '../../components/HashTagSearch';

export interface IPostFormPageProps {
}

export default (props: IPostFormPageProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'normal'} text={'#해시태그'}/>
            <HashTagSearch />
        </Wrapper>
    )
}

const Wrapper = styled.div`
    display: grid;
    grid-auto-rows: 56px auto;
    height: 100%;
    grid-template-areas:
    "CH"
    "CC";
`;
