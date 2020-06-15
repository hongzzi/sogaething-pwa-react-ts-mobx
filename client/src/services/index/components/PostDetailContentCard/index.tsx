import * as React from 'react';
import styled from '~/styled';

import moment from 'moment';

import useStores from '../../helpers/useStores';
import { IPost } from '../../pages/post/[pid]/index';

export interface IPostDetailContentCardProps {
  data: IPost,
  loading: boolean,
}

export default function PostDetailContentCard(props: IPostDetailContentCardProps) {
  const store = useStores();
  const { data, loading } = props;
  const mnt = moment(data.modifiedDate);
  const diffTime = moment.duration(mnt.diff(moment())).asHours();

  return (
    <Wrapper>
      <Title>{data.title}</Title>
      <HashTag>
        {data.hashtag
          .map((tag, index) => (<span key={index}>#{tag} </span>),
          )}
      </HashTag>
      <SpanWrapper>
        <Span>{data.category}</Span>
        <Span>{diffTime >= -168 ? mnt.fromNow() : mnt.format('YY년 M월 D일')}</Span>
        <Span>{data.viewCount}</Span>
      </SpanWrapper>
      <Content>
        {data.contents}
      </Content>
    </Wrapper>
  );
}

const Wrapper = styled.div`
    display: grid;
    width: 100%;
    height: 100%;
    padding: 1rem;
`

const Title = styled.div`
    font-size: 1.2rem;
    color: #222;
    font-weight: bold;
    font-stretch: normal;
    font-style: normal;
    line-height: normal;
    letter-spacing: normal;
`

const HashTag = styled.div`
    font-size: 0.8rem;
    color: #222b45;
`

const SpanWrapper = styled.div`
`

const Span = styled.span`
    font-size: 0.8rem;
    font-weight: bold;
    color: #a7a7a7;
    padding: 0 0.5rem 0 0;
`

const Content = styled.div`
    font-size: 0.9rem;
    line-height: 1.2rem;
    padding: 0.8rem 0 4rem 0;
`
