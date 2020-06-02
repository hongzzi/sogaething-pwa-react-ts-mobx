import * as React from 'react';
import { useState } from 'react';
import styled from '~/styled';
import useStores from '../../helpers/useStores';

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
    const [hashTag, setHashTag] = useState(hStore.hashtag);

    const handleClick = (event: any) => {
        if (confirm('해시태그를 삭제할까요?')) {
            hStore.removeHashtag(event.target.value);
            setHashTag(hStore.hashtag);
        }
    }
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();
        setValue(event.target.value);
    }
    const handleSubmit = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if ((event.keyCode === 13 ) || (event.keyCode === 32)) {
            if (hStore.hashtag.length > 6) {
                alert('해시태그 6개를 전부 입력하셨습니다!');
                setValue('');
            } else if (value !== '') {
                hStore.setHashtag(value.trim());
                setHashTag(hStore.hashtag);
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
                    <AutoCompleteField> test </AutoCompleteField>
                </SearchBar>
                <HashTags>
    {hStore.hashtag.map((hashtag: string | number | undefined, index: string | number | undefined) => (<Tag onClick={handleClick} key={index} value={hashtag}>{hashtag}</Tag>))}
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

const AutoCompleteField = styled.div`
    display: absolute;
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

const Tag = styled.button`
    display: inline-block;
    margin: 0.3rem;
    padding: 0.3rem 1rem;
    font-size: 12px;
    border-radius: 5px;
    border: transparent;
    background: #9ccc;
    line-height: 1.5rem;
`
