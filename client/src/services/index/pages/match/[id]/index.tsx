import { useRouter } from 'next/router';
import * as React from 'react';
import { useGetMatchingQuery } from '~/generated/graphql';
import CategoryHeader from '~/services/index/components/CategoryHeader';
import MatchCardList from '~/services/index/components/MatchCardList';
import styled from '~/styled';

export default () => {
  const router = useRouter();
  // const { id } = router.query();
  const { data, loading, error } = useGetMatchingQuery();

  return (
    <Wrapper>
      <CategoryHeader type={'normal'} text={'구매하기'} />
      {
        loading &&
        <p>loading…</p>
      }
      {
        error &&
        <p>error</p>
      }
      {data && data.findMatchingByUserId && (
        <MainContainer>
          <MatchCardList data={data.findMatchingByUserId} />
        </MainContainer>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`

`;

const MainContainer = styled.div`
    margin-top: 56px;
    padding: 16px;
`;
