import * as React from "react";
import styled from "~/styled";

export default () => {
  return (
    <Card>
      <WrapperText>
          #맥북<br />
          1,000,000
      </WrapperText>
    </Card>
  );
};

export const Card = styled.div`
  display: inline-block;
  padding: 5px;
  margin-right: 8px;
  width: 87px;
  height: 87px;
  border-radius: 5px;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0), gray),
    url("https://img.uxfree.com/wp-content/uploads/2019/02/free-macbook-pro-side-view-mockup-psd-1000x640.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: 50% 50%;
`;

const WrapperText = styled.div`
  margin-top: 60%;
  font-size: 10px;
  color: white;
  font-weight: bold;
`;
