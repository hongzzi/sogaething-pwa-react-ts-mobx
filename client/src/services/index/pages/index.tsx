import { toJS } from 'mobx';
import { useObserver } from 'mobx-react';
import { NextPage, NextPageContext } from 'next';
import { useRouter } from 'next/router';
import * as React from 'react';
import { useCookies } from 'react-cookie';
import { useGetUserInfoQuery } from '~/generated/graphql';
import styled from '~/styled';
import CardList from '../components/CardList';
import CategoryHeader from '../components/CategoryHeader';
import CommonBtn from '../components/CommonBtn';
import Loader from '../components/Loader';
import MainUserCard from '../components/MainUserCard';
import Nav from '../components/Nav';
import Pikachu from '../components/Pikachu';
import ProductCardList from '../components/ProductCardList';
import SearchBar from '../components/SearchBar';
import useStores from '../helpers/useStores';
import ChatService from '../service/ChatService';

const PageIndex: NextPage = (props) => {
  const { pageStore, authStore } = useStores();
  const router = useRouter();
  React.useEffect(() => {
    if (authStore.token === '' || !authStore.token) {
      router.push('/signin');
    }
  });
  const handleMoreCards = () => {
    // console.log(authStore.token);
    router.push('/category');
  };
  return (
    <Layout>
      <Container>
        <SearchBar />
        <StyledMainUserCard />
        <Line>
          <CategoryText>'내가 본'</CategoryText> 매물
        </Line>
        <CardList />
        <Line>
          <CategoryText>'최신'</CategoryText> 중고매물
        </Line>
        <ProductCardList />
        <WrapperAlignCenter onClick={handleMoreCards}>
          <CommonBtn type={'common'} text={'더보기'} />
        </WrapperAlignCenter>
      </Container>
      <Nav />
    </Layout>
  );
}

PageIndex.getInitialProps = async (ctx: NextPageContext) => {
  return {test : 'test'}
}

export default PageIndex;

const Layout = styled.div`
  position: relative;
  padding-bottom: 48px;
`;

export const Container = styled.div`
  padding: 1rem;
`;

const StyledMainUserCard = styled(MainUserCard)``;

const CategoryText = styled.p`
  display: inline-block;
  font-size: 12px;
  color: ${(props) => props.theme.mainCategoryTextColor};
`;

const WrapperAlignCenter = styled.div`
  text-align: center;
`;

const Title = styled.h1`
  font-size: 2rem;
  margin: 0 0 1rem;
`;

const Line = styled.div`
  margin: 22px 0.5rem 0rem 0;
  font-size: 11px;
`;

const Code = styled.div`
  font-family: monospace;
  display: inline-block;
  background-color: ${(props) => props.theme.blue[0]};
  color: ${(props) => props.theme.blue[8]};
  font-size: 0.75rem;
  border-radius: 0.125rem;
  padding: 0.0625rem 0.125rem;
  margin-right: 0.25rem;
  font-weight: 700;
`;

const Author = styled.div`
  font-family: monospace;
  display: inline-block;
  background-color: ${(props) => props.theme.green[0]};
  color: ${(props) => props.theme.green[8]};
  font-size: 0.75rem;
  border-radius: 0.125rem;
  padding: 0.0625rem 0.125rem;
  margin-right: 0.25rem;
  font-weight: 700;
`;
