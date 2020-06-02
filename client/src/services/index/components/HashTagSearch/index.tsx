import * as React from 'react';
import styled from '~/styled';
import useStores from '../../helpers/useStores';
import { useState } from 'react';

export interface IHashTagSearchProps {
    type: 'post' | 'match'
}

export default function HashTagSearch(props: IHashTagSearchProps) {
    const { type } = props;
    const store = useStores();
    let hStore: any;
    if (type === 'match') {
        hStore = store.matchStore;
    } else if (type === 'post') {
        hStore = store.postStore
    }
    const [value, setValue] = useState('');

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();
        setValue(event.target.value);
    }
    const handleSubmit = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.keyCode === 13) {
            if (value !== '') {
                hStore.setHashtag(value);
                setValue('');
            }
        }
    }

    return (
        <Wrapper>
            <Container>
                <SearchBar>
                    <Shap>#</Shap>
                    <Input value={value} type={'text'} onChange={handleChange} onKeyUp={handleSubmit} />
                </SearchBar>
                <HashTags>
                    {hStore.hashtag.map((hashtag: React.ReactNode, index: string | number | undefined) => (<Tag key={index}> {hashtag} </Tag>))}
                </HashTags>
            </Container>
        </Wrapper>
    )
}

const Wrapper = styled.div`
    display: flex;
    grid-area: CC;
`

const Container = styled.div`
    width: 100%;
    height: 100%;
    padding: 2rem 3rem;
`

const SearchBar = styled.div`
    width: 100%;
    height: 3rem;
    border-bottom: solid 3px #333;
    display: flex;
    align-items: center;
`

const Shap = styled.span`
    font-size: 1.4rem;
    font-weight: bold;
    vertical-align: middle;
    margin: 0.5rem;
`

const Input = styled.input`
    height: 2rem;
    width: 80%;
    font-size: 1rem;
    border: none;
    :focus {
        outline:  none !important;
    }
`

const HashTags = styled.div`
    padding: 2rem 0;
    width: 100%;
    height: 100%;
    white-space: normal;
`

const Tag = styled.div`
    display: inline;
    margin: 0.5rem;
    padding: 0.5rem 1rem;
    font-size: 12px;
    border-radius: 5px;
    background: #9ccc;
    line-height: 3rem;
`
