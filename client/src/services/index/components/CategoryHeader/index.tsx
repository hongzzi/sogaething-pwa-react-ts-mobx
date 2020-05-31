import * as React from 'react';
import { useRouter } from 'next/router';
import styled from '~/styled';
import HeaderBack from '../../assets/img/header-back.png?url';
import HeaderCheck from '../../assets/img/header-check.png?url';
import HeacerMenu from '../../assets/img/header-menu.png?url';
import CustomIcon from '../CustomIcon';

interface ICategoryHeader {
  type: 'check' | 'back-check' | 'normal' | 'chat';
  text?: string;
}

export default (props: ICategoryHeader) => {
  const router = useRouter();
  const handleBackIconClick = () => {
    router.back();
  }
  return (
    <Wrapper>
      {props.type === 'check' && <div>check</div>}
      {props.type === 'back-check' && <div>back-check</div>}
      {props.type === 'normal' && (
        <>
          <TextWrapper>
            <WrapperIcon onClick={handleBackIconClick}>
              <CustomIcon url={HeaderBack} />
            </WrapperIcon>
            <Text>{props.text ? props.text : '소개띵'}</Text>
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
  grid-area: CH;
  width: 100vw;
  height: 56px;
  padding: 2vh;
  box-shadow: 0 2px 4px 0 rgba(244, 244, 244, 0.5);
  position: fixed;
  top: 0;
  background-color: white;
  z-index: 100;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const WrapperIcon = styled.div``;

const TextWrapper = styled.div`
  display: flex;
  align-items: center;
`;

const StyledCustomIcon = styled(CustomIcon)`
  display: inline;
`;

const PointText = styled.div`
  font-size: 34px;
  font-family: GmarketSansTTF;
  font-weight: bold;
  display: inline;
  color: ${(props) => props.theme.pointFontColor};
`;

const Text = styled.div`
  font-size: 18px;
  font-weight: bold;
  color: ${(props) => props.theme.mainFontColor};
`;
