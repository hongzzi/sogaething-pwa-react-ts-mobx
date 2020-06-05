import * as React from 'react';
import { useState } from 'react';
import { useApolloClient } from 'react-apollo-hooks';
import { useGetAutoCompleteQuery } from '~/generated/graphql';
import styled from '~/styled';
import useStores from '../../helpers/useStores';
import Autocomplete from './Autocomplete';
import { useObserver } from 'mobx-react';

export interface IHashTagSearchProps {
    type: 'post' | 'match'
}

export interface ITag {
    hashtag: string,
    count: number,
}

function useTagData(type: string) {
    const store = useStores();
    const vStore = store.visiableStore;
    let hStore: any;
    if (type === 'match') {
        hStore = store.matchStore;
    } else if (type === 'post') {
        hStore = store.postStore
    }

    return useObserver(() => ({
      // useObserver를 사용해서 리턴하는 값의 업데이트를 계속 반영한다
      hashtag: hStore.hashtag,
      tag: hStore.tag,
      visiable: vStore.visiable,
    }));
  }

export default function HashTagSearch(props: IHashTagSearchProps) {
    const { type } = props;
    const { hashtag, tag, visiable } = useTagData(type);
    const store = useStores();
    let hStore: any;
    if (type === 'match') {
        hStore = store.matchStore;
    } else if (type === 'post') {
        hStore = store.postStore
    }

    const handleClick = (event: any) => {
        if (confirm('해시태그를 삭제할까요?')) {
            hStore.removeHashtag(event.target.value);
        }
    }
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();
        const val = event.target.value;
        hStore.setTag(val);
    }

    const handleSubmit = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if ((event.keyCode === 13) || (event.keyCode === 32)) {
            if (hashtag.length >= 6) {
                alert('해시태그 6개를 전부 입력하셨습니다!');
                hStore.setTag('');
            } else if (tag !== '') {
                hStore.setHashtag(tag.trim());
                hStore.setTag('');
            }
        }
    }
    const handleOn = () => {
        store.visiableStore.setOn();
    }

    const handleOff = () => {
        setTimeout(() => {
            store.visiableStore.setOff();
          }, 100);
    }

    return (
        <Wrapper>
            <Container>
                <SearchBar>
                    <Shap>#</Shap>
                    <Input value={tag} type={'text'} onChange={handleChange} onKeyUp={handleSubmit} onFocus={handleOn} onBlur={handleOff} />
                </SearchBar>
                <Autocomplete type={type} />
                <Info>
                    <Line> - 물품명, 브랜드, 년식, 상태 등을 적어주세요.</Line>
                    <Line> - 엔터 혹은 띄어쓰기를 하시면 해시태그가 등록됩니다.</Line>
                    <Line> - 해시태그는 여섯개까지 등록할 수 있습니다.</Line>
                </Info>
                <HashTags>
                    { hashtag.map((hashtag: string | number | undefined, index: string | number | undefined) => (
                        <Tag onClick={handleClick} key={index} value={hashtag}> {hashtag} </Tag>
                    ))}
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

const Info = styled.div`
    position: relative;
    height: 8rem;
    padding: 1rem;
`

const Line = styled.div`
    font-size: 0.5rem;
`

const HashTags = styled.div`
    position: relative;
    padding: 2rem 0;
    width: 100%;
    white-space: normal;
`

const Tag = styled.button`
    display: inline-block;
    margin: 0.3rem;
    top: 8rem;
    padding: 0.3rem 1rem;
    font-size: 12px;
    border-radius: 5px;
    border: transparent;
    color: #fff;
    background: #259be5;
    line-height: 1.5rem;
`
