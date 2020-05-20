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

export default (props: INav) => {
  const { pageStore } = useStores();
  return (
    <WrapprNav size={props.size}>
      <WrapperNavItem>
        <CustomIcon url={HomeImage} />
      </WrapperNavItem>
      <WrapperNavItem>
        <CustomIcon url={MenuImage} />
      </WrapperNavItem>
      <WrapperNavItem>
        <CustomIcon url={CircleImage} />
      </WrapperNavItem>
      <WrapperNavItem>
        <CustomIcon url={ChatImage} />
      </WrapperNavItem>
      <WrapperNavItem>
        <Link href='/user'>
          <a>
            <CustomIcon url={UserImage} />
          </a>
        </Link>
      </WrapperNavItem>
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
