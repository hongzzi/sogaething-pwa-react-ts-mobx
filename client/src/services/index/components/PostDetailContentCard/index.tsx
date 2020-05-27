import * as React from 'react';
import styled from '~/styled';

import useStores from '../../helpers/useStores';
import { IPost } from '../../pages/post/[pid]/index';

export interface IPostDetailContentCardProps {
  data: IPost,
  loading: boolean,
}

export default function PostDetailContentCard(props: IPostDetailContentCardProps) {
  const store = useStores();
  const { data, loading } = props;
  return (
    <Wrapper>
      <Title>{data.title}</Title>
      <HashTag>
        {data.hashtag
            .map((tag, index) => (<>#{tag.hashtag} </>)
            )}
      </HashTag>
      <Category>{data.category}</Category>
      <Content>
        {data.contents}
      </Content>
    </Wrapper>
  );
}

const Wrapper = styled.div`
    display: grid;
    row-gap: 0.4rem;
    width: 100%;
    height: 100%;
    padding: 1rem;
`

const Title = styled.div`
    font-size: 1.2rem;
    color: #8f9bb3;
    font-weight: bold;
    font-stretch: normal;
    font-style: normal;
    line-height: normal;
    letter-spacing: normal;
`

const HashTag = styled.div`
    font-size: 0.3rem;
    color: #222b45;
`

const Category = styled.div`
    font-size: 0.3rem;
    font-weight: bold;
    color: #a7a7a7;
`

const Content = styled.div`
    font-size: 0.8rem;
    line-height: 1.2rem;
    padding: 0.8rem 0 4rem 0;
`
