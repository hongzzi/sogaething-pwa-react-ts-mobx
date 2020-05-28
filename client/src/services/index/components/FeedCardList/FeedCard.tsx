import * as React from "react";
import styled from "~/styled";
import like from "../../assets/img/like.png?url";
import pin from "../../assets/img/pin-fill.png?url";
import CircleImageView from "../CircleImageView";
import CustomIcon from "../CustomIcon";
import ImageView from "../ImageView";

export default () => {
  return (
    <Wrapper>
      <ImageView
        size={228}
        src={
          "https://post-phinf.pstatic.net/MjAxOTA3MTBfMTE0/MDAxNTYyNzQ0NTMzNDEx.ks3xOJZyL3q__N9S702Z04vHYEe1cp4wOoQIgidbZrMg.O1NFAFJsyFMKGuGUTPy_4AQY-qp-26dhkyBgrTRTlQgg.JPEG/DSC4297.jpg"
        }
      />
      <WrapperBottomContainer>
        <UserInfoCard>
          <CircleImageView
            size={2}
            src={
              "https://pngimage.net/wp-content/uploads/2018/05/default-user-profile-image-png-6.png"
            }
          />
          <UserInfoNameAddress>
            <UserName>류일한</UserName>
            <WrapperAddress>
              <Icon src={pin} />
              <Address>경기도 수원시 영통동</Address>
            </WrapperAddress>
          </UserInfoNameAddress>
          <Price>1,000,000원</Price>
          <LikeIcon src={like} />
        </UserInfoCard>
        <Address>가전 / 디지털</Address>
        <Content>
          awefhualiwefhuialwehfuialwehfuial fhauiwefhaiwuelfhia luwehfia
          ulwehfiawuehfiauwehfialwefhialwehfui l
          awefhualiwefhuialwehfuialwehfuial fhauiwefhaiwuelfhia luwehfia
          ulwehfiawuehfiauwehfialwefhialwehfui
          lawefhualiwefhuialwehfuialwehfuial fhauiwefhaiwuelfhia luwehfia
          ulwehfiawuehfiauwehfialwefhialwehfui
          lawefhualiwefhuialwehfuialwehfuial fhauiwefhaiwuelfhia luwehfia
          ulwehfiawuehfiauwehfialwefhialwehfui l
        </Content>
      </WrapperBottomContainer>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  border-radius: 6px;
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
