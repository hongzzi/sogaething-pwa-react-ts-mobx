import * as React from 'react';
import styled from '~/styled';
import HeaderBack from '../../assets/img/header-back.png?url';
import HeaderCheck from '../../assets/img/header-check.png?url';
import HeacerMenu from '../../assets/img/header-menu.png?url';
import CustomIcon from '../CustonIcon';

interface ICategoryHeader {
  type: 'check' | 'back-check' | 'normal' | 'chat';
}

export default (props: ICategoryHeader) => {
  return (
    <Wrapper>
      {props.type === 'check' && <div>check</div>}
      {props.type === 'back-check' && <div>back-check</div>}
      {props.type === 'normal' && (
        <>
          <TextWrapper>
            <StyledCustomIcon url={HeaderBack} />
            <Text>Test</Text>
          </TextWrapper>
          <StyledCustomIcon url={HeacerMenu} />
        </>
      )}
      {props.type === 'chat' && (
        <>
          <PointText>채팅</PointText> <StyledCustomIcon url={HeacerMenu} />
        </>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  height: 80px;
  box-shadow: 0 2px 4px 0 rgba(244, 244, 244, 0.5);
  display: flex;
  padding-left: 20px;
  padding-right: 8px;
  justify-content: space-between;
  align-items: center;
`;

const TextWrapper = styled.div`
  display: flex;
  align-items: center;
`;

const StyledCustomIcon = styled(CustomIcon)`
  display: inline;
`;

const PointText = styled.div`
  font-size: 34px;
  display: inline;
  color: ${(props) => props.theme.pointFontColor};
`;

const Text = styled.div`
  font-size: 18px;
  font-weight: bold;
  color: ${(props) => props.theme.mainFontColor};
`;
