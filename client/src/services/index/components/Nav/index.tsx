import { useObserver } from 'mobx-react';
import Link from 'next/link';
import * as React from 'react';
import styled from '~/styled';
import ChatImageFocus from '../../assets/img/chat-focus.png?url';
import ChatImage from '../../assets/img/chat-none.png?url';
import CircleImageFocus from '../../assets/img/circle-focus.png?url';
import CircleImage from '../../assets/img/circle-none.png?url';
import HomeImageFocus from '../../assets/img/home-focus.png?url';
import HomeImage from '../../assets/img/home-none.png?url';
import MenuImageFocus from '../../assets/img/menu-focus.png?url';
import MenuImage from '../../assets/img/menu-none.png?url';
import UserImageFocus from '../../assets/img/user-focus.png?url';
import UserImage from '../../assets/img/user-none.png?url';
import useStores from '../../helpers/useStores';
import CustomIcon from '../CustomIcon';
interface INav {
  size?: {
    height: number;
  };
}

function usePageData() {
  const { pageStore } = useStores();
  return useObserver(() => ({
    // useObserver를 사용해서 리턴하는 값의 업데이트를 계속 반영한다
    clickedIdx: pageStore.clickedIdx,
  }));
}

export default (props: INav) => {
  const { pageStore } = useStores();
  const { clickedIdx } = usePageData();

  return (
    <WrapprNav size={props.size}>
      <Link href='/'>
        <a>
          <WrapperNavItem onClick={() => pageStore.setClickedIdx(0)}>
            {clickedIdx === 0 ? (
              <CustomIcon url={HomeImageFocus} />
            ) : (
              <CustomIcon url={HomeImage} />
            )}
          </WrapperNavItem>
        </a>
      </Link>

      <Link href='/category'>
        <a>
          <WrapperNavItem onClick={() => pageStore.setClickedIdx(1)}>
            {clickedIdx === 1 ? (
              <CustomIcon url={MenuImageFocus} />
            ) : (
              <CustomIcon url={MenuImage} />
            )}
          </WrapperNavItem>
        </a>
      </Link>
      <WrapperNavItem onClick={() => pageStore.setClickedIdx(2)}>
        {clickedIdx === 2 ? (
          <CustomIcon url={CircleImageFocus} />
        ) : (
          <CustomIcon url={CircleImage} />
        )}
      </WrapperNavItem>
      <Link href='/chat'>
        <a>
          <WrapperNavItem onClick={() => pageStore.setClickedIdx(3)}>
            {clickedIdx === 3 ? (
              <CustomIcon url={ChatImageFocus} />
            ) : (
              <CustomIcon url={ChatImage} />
            )}
          </WrapperNavItem>
        </a>
      </Link>
      <Link href='/user'>
        <a>
          <WrapperNavItem onClick={() => pageStore.setClickedIdx(4)}>
            {clickedIdx === 4 ? (
              <CustomIcon url={UserImageFocus} />
            ) : (
              <CustomIcon url={UserImage} />
            )}
          </WrapperNavItem>
        </a>
      </Link>
    </WrapprNav>
  );
};

const WrapprNav = styled.nav`
  position: fixed;
  display: flex;
  justify-content: space-around;
  bottom: 0;
  height: ${(props: Pick<INav, 'size'>) =>
    props.size ? props.size.height : '48px'};
  width: 100%;
  background-color: ${(props) => props.theme.mainBGcolor};
  align-items: center;
`;

const WrapperNavItem = styled.div``;
