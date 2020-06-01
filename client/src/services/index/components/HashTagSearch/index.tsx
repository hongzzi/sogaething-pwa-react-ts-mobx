import * as React from 'react';
import styled from '~/styled';

export interface IHashTagSearchProps {

}

export default function HashTagSearch(props: IHashTagSearchProps) {
    return(
        <Wrapper>
            <HashTagSearch></HashTagSearch>
        </Wrapper>
    )
}

const Wrapper = styled.div`
    display: flex;
`

const HashtagSearchBar = styled.div`
    width: 100%;
    height: 3rem;
`
