import { useObserver } from 'mobx-react';
import * as React from 'react';
import { useGetAutoCompleteQuery } from '~/generated/graphql';
import styled from '~/styled';
import { IHashTagSearchProps } from '.';
import useStores from '../../helpers/useStores';

const Complete = (props: { item: any, type: string }) => {
    const { item, type } = props;
    const store = useStores();
    let hStore: any;
    if (type === 'match') {
        hStore = store.matchStore;
    } else if (type === 'post') {
        hStore = store.postStore
    }
    const handleCilck = (event: any) => {
        if (hStore.hashtag.length > 5) {
            alert('해시태그 6개를 전부 입력하셨습니다!');
            hStore.setTag('');
        } else if (item !== '') {
            hStore.setHashtag(item.hashtag);
            hStore.setTag('');
        }
    }

    return (
        <TagBox onClick={handleCilck}>
            <Name id='hash'>{item.hashtag}</Name>
            <Count>{item.count}</Count>
        </TagBox>
    )
}

function useData() {
    const store = useStores();
    return useObserver(() => ({
      // useObserver를 사용해서 리턴하는 값의 업데이트를 계속 반영한다
      visiable: store.visiableStore.visiable,
    }));
  }

export default (props: IHashTagSearchProps) => {
    const { type } = props;
    const { visiable } = useData();
    const store = useStores();
    let hStore: any;
    if (type === 'match') {
        hStore = store.matchStore;
    } else if (type === 'post') {
        hStore = store.postStore
    }
    const { data } = useGetAutoCompleteQuery({
        variables: {
            input: hStore.tag,
        },
    })
    return (
        <Wrapper visible={visiable}>
            {data && data.autocomplete && data.autocomplete.length > 0 &&
                data.autocomplete.map((data, index) => (
                    <Complete item={data} key={index} type={type} />
                ))
            }
        </Wrapper>
    )
}

const Wrapper = styled.div`
    visibility: ${(props: { visible: boolean }) => props.visible ? 'visible' : 'hidden'};
    z-index: 3;
    position: fixed;
    width: 75%;
    height: auto;
    max-height: 8rem;
    background: #fff;
    border: solid 1px #ccc;
    white-space: nowrap;
    overflow-y: scroll;
    ::-webkit-scrollbar {
        display: none;
    }
`;

const TagBox = styled.div`
    position: relative;
    display: flex;
    justify-content: space-between;
    padding: 0.5rem 1rem;
`

const Name = styled.div`
    color: #6459db;
`

const Count = styled.div`
    color: #ff3d71;
`
