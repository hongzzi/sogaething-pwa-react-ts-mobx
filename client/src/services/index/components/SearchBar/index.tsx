import * as React from 'react';
import styled from '~/styled';
import SearchImage from '../../assets/img/main-search.png?url';
import CustomIcon from '../CustomIcon';

export default () => {
  return (
    <Wrapper>
      <StyledInput type='text' placeholder='해시태그로 검색하기..' />
      <StyledCustomIcon url={SearchImage} />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  height: 56px;
  border-radius: 29px;
  border: solid 2px ${(props) => props.theme.searchBarColor};
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-shadow: 0 2px 4px 0 #bfbfbf;
`;

const StyledInput = styled.input`
  font-size: 16px;
  margin: 16px 19px 16px 19px;
  flex-grow: 8.5;
  border: 0;
  -webkit-appearance: none;
       -moz-appearance: none;
            appearance: none;
            resize: none;
            outline: none;
`;

const StyledCustomIcon = styled(CustomIcon)`
    flex-grow: 1.5;
`;
