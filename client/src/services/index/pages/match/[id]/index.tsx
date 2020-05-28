import { useRouter } from 'next/router';
import * as React from 'react';
import CategoryHeader from '~/services/index/components/CategoryHeader';
import styled from '~/styled';
import MatchCardList from '~/services/index/components/MatchCardList';

export default () => {
  const router = useRouter();
  return (
    <Wrapper>
      <CategoryHeader type={'normal'} text={'구매하기'} />
      <MainContainer>
          <MatchCardList />
      </MainContainer>
    </Wrapper>
  );
};

const Wrapper = styled.div`
    
`;

const MainContainer = styled.div`
    margin-top: 56px;
    padding: 16px;
`;
