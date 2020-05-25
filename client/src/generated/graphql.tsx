export type Maybe<T> = T | null;
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
};

export type ICreateDetailDealInput = {
  postId: Scalars["Int"];
  userId: Scalars["Int"];
  hashtagId: Scalars["Int"];
};

export type ICreateProductInput = {
  postId: Scalars["Int"];
  price: Scalars["Int"];
  name: Scalars["String"];
  category: Scalars["String"];
  state: Scalars["Boolean"];
};

export type IDetailDeal = {
  dealId: Scalars["ID"];
  post: IPost;
  user: IUser;
  hashtag: IHashtag;
  createdDate?: Maybe<Scalars["String"]>;
  modifiedDate?: Maybe<Scalars["String"]>;
};

export type IDetailDealOutput = {
  postId: Scalars["Int"];
  imgPath: Scalars["String"];
  title: Scalars["String"];
  category: Scalars["String"];
  hashtag: Scalars["String"];
  contents: Scalars["String"];
  price: Scalars["Int"];
  buyerId: Scalars["Int"];
  sellerId: Scalars["Int"];
  address: Scalars["String"];
};

export type IHashtag = {
  hashtagId: Scalars["ID"];
  product: IProduct;
  hashtag: Scalars["String"];
};

export type ILoginUserInput = {
  provider: Scalars["String"];
  token: Scalars["String"];
};

export type ILoginUserOutput = {
  token: Scalars["String"];
};

export type IMutation = {
  createProduct?: Maybe<IProduct>;
  createDetailDeal?: Maybe<IDetailDealOutput>;
  loginUser?: Maybe<ILoginUserOutput>;
};

export type IMutationCreateProductArgs = {
  input: ICreateProductInput;
};

export type IMutationCreateDetailDealArgs = {
  input: ICreateDetailDealInput;
};

export type IMutationLoginUserArgs = {
  input: ILoginUserInput;
};

export type IPost = {
  postId: Scalars["ID"];
  user?: Maybe<IUser>;
  isBuy: Scalars["Boolean"];
  title: Scalars["String"];
  saleDate: Scalars["String"];
  contents: Scalars["String"];
  viewCount: Scalars["Int"];
  deal: Scalars["String"];
  createdDate?: Maybe<Scalars["String"]>;
  modifiedDate?: Maybe<Scalars["String"]>;
};

export type IProduct = {
  productId: Scalars["ID"];
  post?: Maybe<IPost>;
  name: Scalars["String"];
  price: Scalars["Int"];
  category: Scalars["String"];
  state?: Maybe<Scalars["Boolean"]>;
};

export type IQuery = {
  findAllPosts?: Maybe<Array<Maybe<IPost>>>;
  findPostByPostId?: Maybe<IPost>;
  findAllDetailDeals?: Maybe<Array<Maybe<IDetailDeal>>>;
  findDetailDealByPost?: Maybe<Array<Maybe<IDetailDealOutput>>>;
  findAllUsers?: Maybe<Array<Maybe<IUser>>>;
  findAllProducts?: Maybe<Array<Maybe<IProduct>>>;
  findProductById?: Maybe<IProduct>;
};

export type IQueryFindPostByPostIdArgs = {
  id?: Maybe<Scalars["Int"]>;
};

export type IQueryFindDetailDealByPostArgs = {
  postId?: Maybe<Scalars["Int"]>;
};

export type IQueryFindProductByIdArgs = {
  productId?: Maybe<Scalars["Int"]>;
};

export type IUser = {
  userId: Scalars["ID"];
  name: Scalars["String"];
  email?: Maybe<Scalars["String"]>;
  imageUrl: Scalars["String"];
  provider: Scalars["String"];
  providerId: Scalars["Int"];
  phone: Scalars["String"];
  address: Scalars["String"];
  trust: Scalars["Int"];
};
export type IGetLoginMutationVariables = {
  input: ILoginUserInput;
};

export type IGetLoginMutation = { __typename?: "Mutation" } & {
  loginUser: Maybe<
    { __typename?: "LoginUserOutput" } & Pick<ILoginUserOutput, "token">
  >;
};

import gql from "graphql-tag";
import * as React from "react";
import * as ReactApollo from "react-apollo";
import * as ReactApolloHooks from "react-apollo-hooks";
export type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>>;

export const GetLoginDocument = gql`
  mutation getLogin($input: LoginUserInput!) {
    loginUser(input: $input) {
      token
    }
  }
`;
export type IGetLoginMutationFn = ReactApollo.MutationFn<
  IGetLoginMutation,
  IGetLoginMutationVariables
>;

export const GetLoginComponent = (
  props: Omit<
    Omit<
      ReactApollo.MutationProps<IGetLoginMutation, IGetLoginMutationVariables>,
      "mutation"
    >,
    "variables"
  > & { variables?: IGetLoginMutationVariables }
) => (
  <ReactApollo.Mutation<IGetLoginMutation, IGetLoginMutationVariables>
    mutation={GetLoginDocument}
    {...props}
  />
);

export type IGetLoginProps<TChildProps = {}> = Partial<
  ReactApollo.MutateProps<IGetLoginMutation, IGetLoginMutationVariables>
> &
  TChildProps;
export function withGetLogin<TProps, TChildProps = {}>(
  operationOptions?: ReactApollo.OperationOption<
    TProps,
    IGetLoginMutation,
    IGetLoginMutationVariables,
    IGetLoginProps<TChildProps>
  >
) {
  return ReactApollo.withMutation<
    TProps,
    IGetLoginMutation,
    IGetLoginMutationVariables,
    IGetLoginProps<TChildProps>
  >(GetLoginDocument, {
    alias: "withGetLogin",
    ...operationOptions
  });
}

export function useGetLoginMutation(
  baseOptions?: ReactApolloHooks.MutationHookOptions<
    IGetLoginMutation,
    IGetLoginMutationVariables
  >
) {
  return ReactApolloHooks.useMutation<
    IGetLoginMutation,
    IGetLoginMutationVariables
  >(GetLoginDocument, baseOptions);
}
