import * as React from 'react';
import styled from '~/styled';

import Categoryheader from '../../../components/CategoryHeader';
import MatchForm from '../../../components/MatchForm';

export interface IMatchFormPageProps {
}

export default (props: IMatchFormPageProps) => {
    return (
        <Wrapper>
            <Categoryheader type={'normal'} text={'매칭조건 입력하기'}/>
            <MatchForm />
        </Wrapper>
    )
}

const Wrapper = styled.div`
    grid-area: CC;
`
