import * as React from 'react';
import styled from '~/styled';
import like from '../../assets/img/like.png?url';
import NoAvatar from '../../assets/img/no-avatar.png?url';
import pin from '../../assets/img/pin-fill.png?url';
import { numberWithCommas } from '../../helpers/comma';
import CircleImageView from '../CircleImageView';
import CustomIcon from '../CustomIcon';
import ImageView from '../ImageView';
import { useRouter } from 'next/router';

export interface IFeedCardProps {
  data: IFeed,
}

export interface IFeed {
  postId: number,
  title: string,
  category: string,
  imgPaths: string[],
  hashtag: string[],
  contents: string,
  price: number,
  user: IUser,
  viewCount: number,
  isBuy: boolean,
  deal: string,
  dealState: string,
  saleDate: string,
  transaction: string,
  createdDate: string,
  modifiedDate: string,
}

export interface IUser {
  name: string,
  address: string,
  trust: number,
  numOfPosts: number,
  imgurl: string,
}

export default (props: IFeedCardProps) => {
  const { data } = props;
  const router = useRouter();
  const handleClick = () => {
    router.push(`/post/${data.postId}`)
  }
  return (
    <Wrapper onClick={handleClick}>
      <ImageView
        size={228}
        src={data.imgPaths[0]}
      />
      <WrapperBottomContainer>
        <UserInfoCard>
          {data.user.imgurl && <CircleImageView size={2} src={data.user.imgurl} />}
          {!data.user.imgurl && <CircleImageView size={2} src={NoAvatar} />}
          <UserInfoNameAddress>
            <UserName>{data.user.name}</UserName>
            <WrapperAddress>
              <Icon src={pin} />
              <Address>{data.user.address}</Address>
            </WrapperAddress>
          </UserInfoNameAddress>
          <Price>{numberWithCommas(data.price)}원</Price>
          <LikeIcon src={like} />
        </UserInfoCard>
        <Address>{data.category}</Address>
        <Content>
          {data.contents}
        </Content>
      </WrapperBottomContainer>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  border-radius: 6px;
  min-height: 300px;
  overflow: hidden;
  margin-top: 30px;
  border: 1px solid #c5cee0;
`;

const WrapperBottomContainer = styled.div`
  padding: 15px;
  max-height: 180px;
  align-items: center;
`;

const UserInfoCard = styled.div`
  display: flex;
  border-bottom: 1px solid #edf1f7;
  align-items: center;
  padding-bottom: 10px;
`;

const UserInfoNameAddress = styled.div`
  margin-left: 8px;
  display: flex;
  flex-direction: column;
  margin-bottom: 8px;
  flex-grow: 2;
`;

const UserName = styled.div`
  font-size: 16px;
  font-weight: bold;
`;

const Address = styled.div`
  display: inline;
  font-size: 8px;
  color: ${(props) => props.theme.subFontColor};
`;

const WrapperAddress = styled.div`
  display: flex;
  align-items: center;
`;

const Price = styled.div`
  font-size: 16px;
  font-weight: bold;
`;

const Content = styled.div`
  overflow: hidden;
  text-overflow: ellipsis;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 3; /* ellipsis line */
  -webkit-box-orient: vertical;
`;

const Icon = styled.img`
  width: 9px;
  height: 8px;
  margin: 0;
`;

const LikeIcon = styled.img`
  width: 40px;
  height: 40px;
  margin: 0;
`;
