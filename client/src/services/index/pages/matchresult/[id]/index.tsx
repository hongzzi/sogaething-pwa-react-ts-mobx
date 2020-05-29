import { useRouter } from 'next/router';
import * as React from 'react';
import CategoryHeader from '~/services/index/components/CategoryHeader';
import MatchCardList from '~/services/index/components/MatchCardList';
import styled from '~/styled';
import FeedCardList from '~/services/index/components/FeedCardList';

export default () => {
  const router = useRouter();
  return (
    <Wrapper>
      <CategoryHeader type={'normal'} text={'매칭결과'} />
      <MarginTopCategoryHeaderContainer>
          <HashTagHeader>
            #맥북
          </HashTagHeader>
          <FeedCardList />
      </MarginTopCategoryHeaderContainer>
    </Wrapper>
  );
};

const Wrapper = styled.div`

`;

export const MarginTopCategoryHeaderContainer = styled.div`
    margin-top: 56px;
    padding: 16px;
`;

const HashTagHeader = styled.div`
    color: #8f9bb3;
    background-color: #e4e9f2;
    border: 1px solid #8f9bb3;
    border-radius: 6px;
    font-size: 15px;
    padding: 10px 15px 10px 15px;
`;
