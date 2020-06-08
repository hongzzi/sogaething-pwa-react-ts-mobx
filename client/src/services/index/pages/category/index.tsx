import { useRouter } from 'next/router';
import * as React from 'react';
import {
  FaBabyCarriage,
  FaBook,
  FaDog,
  FaShoppingBag,
  FaTshirt,
  FaUtensils,
} from 'react-icons/fa';
import { GiHeartBottle, GiLargeDress, GiSofa } from 'react-icons/gi';
import { IoLogoGameControllerB, IoMdFootball } from 'react-icons/io';
import { MdComputer } from 'react-icons/md';
import styled from '~/styled';
import Categoryheader from '../../components/CategoryHeader';
import Nav from '../../components/Nav';

export interface ICategoryProps {}

// export type categoryItems
//     = '디지털/가전'
//     |'가구'
//     |'유아동'
//     |'생활'
//     |'스포츠'
//     |'여성의류'
//     |'여성잡화'
//     |'남성패션'
//     |'게임'
//     |'뷰티'
//     |'반려동물'
//     |'도서';

export const categoryItems: {[key: string]: string} = {
  1: '디지털/가전',
  2: '가구',
  3: '유아동',
  4: '생활/가공식품',
  5: '스포츠/레저',
  6: '여성의류',
  7: '여성잡화',
  8: '남성패션/잡화',
  9: '게임/취미',
  10: '뷰티/미용',
  11: '반려동물용품',
  12: '도서/티켓/음반',
};

export default (props: ICategoryProps) => {
  const router = useRouter();
  const handleCategoryItemClick = (category: number) => {
    router.push(`/category/${category}`);
  };
  return (
    <Wrapper>
      <Categoryheader type={'chat'} text={'카테고리'} />
      <Container>
        <WrapperCategoryItem>
          <MdComputer size={25} color={'black'} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(1)}>
            디지털/가전
          </CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem onClick={() => handleCategoryItemClick(2)}>
          <GiSofa size={25} />{' '}
          <CategoryItemText>가구</CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem>
          <FaBabyCarriage size={25} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(3)}>
            유아동
          </CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem>
          <FaUtensils size={25} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(4)}>
            생활/가공식품
          </CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem>
          <IoMdFootball size={25} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(5)}>
            스포츠/레저
          </CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem>
          <GiLargeDress size={25} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(6)}>
            여성의류
          </CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem>
          <FaShoppingBag size={25} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(7)}>
            여성잡화
          </CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem>
          <FaTshirt size={25} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(8)}>
            남성패션/잡화
          </CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem>
          <IoLogoGameControllerB size={25} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(9)}>
            게임/취미
          </CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem>
          <GiHeartBottle size={25} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(10)}>
            뷰티/미용
          </CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem onClick={() => handleCategoryItemClick(11)}>
          <FaDog size={25} />{' '}
          <CategoryItemText>반려동물용품</CategoryItemText>
        </WrapperCategoryItem>
        <WrapperCategoryItem>
          <FaBook size={25} />{' '}
          <CategoryItemText onClick={() => handleCategoryItemClick(12)}>
            도서/티켓/음반
          </CategoryItemText>
        </WrapperCategoryItem>
      </Container>
      <Nav />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  height: 100%;
`;

const Container = styled.div`
  padding: 56px 16px 16px 16px;
`;

const WrapperCategoryItem = styled.div`
  width: 100%;
  display: flex;
  padding: 10px 0 10px 0;
  align-items: center;
`;

const CategoryItemText = styled.div`
  display: flex;
  align-items: center;
  padding-left: 5vw;
  font-size: 14px;
  height: 25px;
`;

const CategoryHeaderText = styled.div`
  font-size: 24px;
`;
