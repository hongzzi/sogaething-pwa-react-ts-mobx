import * as React from 'react';
import styled from '~/styled';

interface IBtnProps {
  type: 'common' | 'primary' | 'disable' | 'chatting';
  text: string;
}

export default (props: IBtnProps) => {
  return (
    <Wrapper type={props.type} text={props.text}>
      {props.text}
    </Wrapper>
    );
};

const Wrapper = styled.div<IBtnProps>`
  padding: 0.7rem 1.2rem;
  display: inline-block;
  border-radius: 6px;
  color: ${(props) => props.theme.button[props.type].color};
  background-color: ${(props) => props.theme.button[props.type].bodyColor};
  border: 1px solid ${(props) => props.theme.button[props.type].borderColor};
`;
