import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../../../components/CategoryHeader';
import HashTagSearch from '../../../../components/HashTagSearch';

export interface IMatchHashTagProps {
}

export default (props: IMatchHashTagProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'normal'} text={'#해시태그'}/>
            <HashTagSearch type={'match'}/>
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
