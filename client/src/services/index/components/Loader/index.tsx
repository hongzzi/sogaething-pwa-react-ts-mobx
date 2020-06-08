import * as React from 'react';
import { keyframes } from 'styled-components';
import styled from '~/styled';

export default () => {
  return (
    <Wrapper>
      <LoaderHeart>
        <LoaderHeartDiv>
          <LoaderLeft />
          <LoaderRight />
        </LoaderHeartDiv>
      </LoaderHeart>
    </Wrapper>
  );
};
const Wrapper = styled.div`
  height: 100vh;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-bottom: 12vh;;
`;

const LoaderHeart = styled.div`
  display: inline-block;
  position: relative;
  width: 80px;
  height: 80px;
  transform: rotate(45deg);
  transform-origin: 40px 40px;
`;

const LoadersHeartKeyframe = keyframes`
    0% {
      transform: scale(0.95);
    }
    5% {
      transform: scale(1.1);
    }
    39% {
      transform: scale(0.85);
    }
    45% {
      transform: scale(1);
    }
    60% {
      transform: scale(0.95);
    }
    100% {
      transform: scale(0.9);
    }
`;

const LoaderHeartDiv = styled.div`
  top: 32px;
  left: 32px;
  position: absolute;
  width: 32px;
  height: 32px;
  background: ${(props) => props.theme.pointFontColor};
  animation: ${LoadersHeartKeyframe} 1.2s infinite
    cubic-bezier(0.215, 0.61, 0.355, 1);

  &::after ::before {
    content: " ";
    position: absolute;
    display: block;
    width: 32px;
    height: 32px;
    background: inherit;
  }
`;
const LoaderLeft = styled.div`
    background: inherit;
    position: absolute;
    content: " ";
    display: block;
    width: 32px;
    height: 32px;
    left: -24px;
    border-radius: 50% 0 0 50%;
`;

const LoaderRight = styled.div`
  &::after {
    background: ${(props) => props.theme.pointFontColor};
    position: absolute;
    content: " ";
    display: block;
    width: 32px;
    height: 32px;
    top: -24px;
    border-radius: 50% 50% 0 0;
  }
`;
