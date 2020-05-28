import * as React from 'react';
import styled from '~/styled';
import CircleImageView from '../CircleImageView';

export default () => {
    return(
        <ChatListItem>
          <CircleImageView
            size={2}
            src={
              'https://pngimage.net/wp-content/uploads/2018/05/default-user-profile-image-png-6.png'
            }
          />
          <WrapperText>
            <Line fontSize={14}>류일한</Line>
            <WrapperInnerText>
              <Line fontSize={10} float={'left'}>
                계좌 알려주세요..
              </Line>
              <Line fontSize={10} float={'right'}>
                10분 전
              </Line>
            </WrapperInnerText>
          </WrapperText>
        </ChatListItem>
    )
}

const ChatListItem = styled.div`
  display: flex;
  margin-top: 2vh;
    margin-bottom: 3vh;
  width:100%;
`;

const WrapperText = styled.div`
  margin-left: 2vw;
  width:100%;
  padding-bottom: 12px;
  border-bottom: 1px solid lightgray;
`;

const WrapperInnerText = styled.div`
  position: relative;
  width: 100%;
`;

interface IText {
  fontSize: number;
  float?: 'left' | 'right';
}

const Line = styled.div<IText>`
  font-size: ${(props) => props.fontSize + 'px'};
  float: ${props => props.float};
`;
