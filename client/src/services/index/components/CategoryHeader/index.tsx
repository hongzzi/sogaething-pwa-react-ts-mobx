import { useObserver } from 'mobx-react';
import { useRouter } from 'next/router';
import * as React from 'react';
import styled from '~/styled';
import HeaderBack from '../../assets/img/header-back.png?url';
import HeaderCheck from '../../assets/img/header-check.png?url';
import HeacerMenu from '../../assets/img/header-menu.png?url';
import useStores from '../../helpers/useStores';
import CustomIcon from '../CustomIcon';

interface ICategoryHeader {
  type: 'check' | 'back-check' | 'normal' | 'chat';
  text?: string;
  backHome?: boolean;
}

export default (props: ICategoryHeader) => {
  const router = useRouter();
  const handleBackIconClick = () => {
    if (props.backHome || router.query.isPosted) {
      router.push('/main');
    } else {
      router.back();
    }
  }
  return (
    <Wrapper>
      {props.type === 'check' && <div>check</div>}
      {props.type === 'back-check' && <div>back-check</div>}
      {props.type === 'normal' && (
        <>
            <WrapperIcon onClick={handleBackIconClick}>
              <CustomIcon url={HeaderBack} />
            </WrapperIcon>
            <Text>{props.text ? props.text : ''}</Text>
          <StyledCustomIcon url={HeacerMenu} />
        </>
      )}
      {props.type === 'chat' && (
        <>
          <PointText>{props.text}</PointText> <StyledCustomIcon url={HeacerMenu} />
        </>
      )}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  grid-area: CH;
  width: 100vw;
  height: 56px;
  padding: 2vh 1vh;
  /* box-shadow: 0 2px 4px 0 rgba(220, 220, 220, 0.5); */
  border-bottom: solid 1px #dcdcdc;
  position: fixed;
  top: 0;
  background-color: white;
  z-index: 100;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

const WrapperIcon = styled.div``;

const StyledCustomIcon = styled(CustomIcon)`
  display: inline;
`;

const PointText = styled.div`
  padding: 0 1rem;
  font-size: 24px;
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
