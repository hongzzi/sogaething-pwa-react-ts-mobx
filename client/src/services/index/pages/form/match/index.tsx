import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../../components/CategoryHeader';
import MatchForm from '../../../components/MatchForm';

export interface IMatchFormPageProps {
}

export default (props: IMatchFormPageProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'normal'} text={'매칭카드 등록하기'}/>
            <MatchForm />
        </Wrapper>
    )
}

const Wrapper = styled.div`
    display: grid;
    grid-auto-rows: 56px auto;
    height: 100%;
    grid-template-areas:
    "CH"
    "CC"
    ;
`
