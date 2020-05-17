import styled from '~/styled';
import CategoryHeader from '../components/CategoryHeader';
import CommonBtn from '../components/CommonBtn';
import MainUserCard from '../components/MainUserCard';
import Nav from '../components/Nav';
import Pikachu from '../components/Pikachu';
import SearchBar from '../components/SearchBar';
import useStores from '../helpers/useStores';
import CardList from '../components/CardList';

export default function PageIndex() {
  const store = useStores();

  return (
    <Layout>
      <Container>
        <SearchBar />
        <StyledMainUserCard/>
        <Line><CategoryText>'Category'</CategoryText>의 인기글</Line>
        <CardList />
      </Container>
      <Nav />
    </Layout>
  );
}

const Layout = styled.div`
  position: relative;
`;

const Container = styled.div`
  padding: 1rem;
`;

const StyledMainUserCard = styled(MainUserCard)`
`;

const CategoryText = styled.p`
  display: inline-block;
  font-size : 12px;
  color: ${props => props.theme.mainCategoryTextColor};
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
