import { useRouter } from 'next/router';
import * as React from 'react';
import CategoryHeader from '~/services/index/components/CategoryHeader';
import FeedCardList from '~/services/index/components/FeedCardList';
// import MatchCardList from '~/services/index/components/MatchCardList';
import styled from '~/styled';

import { useGetMatchResultsQuery } from '~/generated/graphql';
import Loader from '~/services/index/components/Loader';

export default () => {
  const router = useRouter();
  const { id } = router.query;
  const { data, loading, error } = useGetMatchResultsQuery({ variables: { input: id } });
  return (
    <Wrapper>
      <CategoryHeader type={'normal'} text={'매칭결과'} />
      {
        loading &&
        <Loader />
      }
      {
        error &&
        <p>error</p>
      }
      {
        data &&
        <MarginTopCategoryHeaderContainer>
          <HashTagHeader>
            {
              data.findAllMatchingHashtagByMatchingId && data.findAllMatchingHashtagByMatchingId.map(
                (value) => (`#${value} `),
              )}
          </HashTagHeader>
          {
            data.matchThings &&
            <FeedCardList data={data.matchThings} />
          }
        </MarginTopCategoryHeaderContainer>
      }
    </Wrapper>
  );
};

const Wrapper = styled.div`

`;

export const MarginTopCategoryHeaderContainer = styled.div`
    margin-top: 56px;
    padding: 16px;
    height: 100px;
`;

const HashTagHeader = styled.div`
    color: #8f9bb3;
    background-color: #e4e9f2;
    border: 1px solid #8f9bb3;
    border-radius: 6px;
    font-size: 15px;
    padding: 10px 15px 10px 15px;
    width: 100%;
    height: auto;
`;
