import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../../../components/CategoryHeader';
import HashTagSearch from '../../../../components/HashTagSearch';

export interface IPostHashtagProps {
}

export default (props: IPostHashtagProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'normal'} text={'#해시태그'}/>
            <HashTagSearch type={'post'} />
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
