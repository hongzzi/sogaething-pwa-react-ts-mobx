export type Maybe<T> = T | null;
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
};

export type ICreatePostInput = {
  uploaderId: Scalars["Int"];
  title: Scalars["String"];
  saleDate: Scalars["String"];
  contents: Scalars["String"];
  deal: Scalars["String"];
};

export type ILoginUserInput = {
  provider: Scalars["String"];
  token: Scalars["String"];
};

export type ILoginUserOutput = {
  token: Scalars["String"];
};

export type IMutation = {
  createPost?: Maybe<IPost>;
  loginUser?: Maybe<ILoginUserOutput>;
};

export type IMutationCreatePostArgs = {
  input: ICreatePostInput;
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

export type IQuery = {
  findAllPosts?: Maybe<Array<Maybe<IPost>>>;
  findPostByPostId?: Maybe<IPost>;
  findAllUsers?: Maybe<Array<Maybe<IUser>>>;
};

export type IQueryFindPostByPostIdArgs = {
  id?: Maybe<Scalars["Int"]>;
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
