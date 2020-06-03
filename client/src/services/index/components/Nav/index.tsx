import { useObserver } from 'mobx-react';
import Link from 'next/link';
import { useRouter } from 'next/router';
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
    modal: pageStore.modal,
  }));
}

export default (props: INav) => {
  const router = useRouter();
  const { pageStore } = useStores();
  const { clickedIdx, modal } = usePageData();
  const handleMatch = () => {
    router.push('/form/match');
  }
  const handlePost = () => {
    router.push('/form/post');
  }

  const handleModal = () => {
    pageStore.toggleModal();
  }
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
      <WrapperNavItem onClick={handleModal}>
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
      <Modal modalState={modal}>
          <ModalItem onClick={handleMatch}>구매하기</ModalItem>
          <ModalItem onClick={handlePost}>판매하기</ModalItem>
          <ModalItem onClick={handleModal}>닫기</ModalItem>
      </Modal>
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
  border-top: solid 1px #dcdcdc;
`;

interface IModalProps {
  modalState: boolean;
}

const Modal = styled.div<IModalProps>`
  visibility: ${(props) => props.modalState ? 'visible' : 'hidden'};
  position: fixed;
  bottom: 0;
  width: 100vw;
  height: 150px;
  background-color: rgba( 255, 255, 255, 0.95 );
  /* transition: visibility 1s ease-in; */
`;

const ModalItem = styled.div`
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: space-around;
  height: 33%;
  width: 100%;
  border-top: 1px solid ${(props) => props.theme.bolderColor};
`;

const WrapperNavItem = styled.div``;
