export type Maybe<T> = T | null;
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
  /** The `Upload` scalar type represents a file upload. */
  Upload: any;
};

export type IAuthInput = {
  accessToken: Scalars["String"];
};

export type IAuthResponse = {
  token?: Maybe<Scalars["String"]>;
  name?: Maybe<Scalars["String"]>;
};

export enum ICacheControlScope {
  Public = "PUBLIC",
  Private = "PRIVATE"
}

export type IMutation = {
  authFacebook?: Maybe<IAuthResponse>;
  authGoogle?: Maybe<IAuthResponse>;
  setTest?: Maybe<IAuthResponse>;
};

export type IMutationAuthFacebookArgs = {
  input: IAuthInput;
};

export type IMutationAuthGoogleArgs = {
  input: IAuthInput;
};

export type IMutationSetTestArgs = {
  input: ITest;
};

export type IQuery = {
  /** A simple type for getting started! */
  hello?: Maybe<Scalars["String"]>;
  hello2?: Maybe<Scalars["String"]>;
};

export type IQueryHello2Args = {
  input: ITest;
};

export type ITest = {
  name?: Maybe<Scalars["String"]>;
};

export type ITestResponse = {
  name?: Maybe<Scalars["String"]>;
};

export type IGetHello2QueryVariables = {};

export type IGetHello2Query = { __typename?: "Query" } & Pick<IQuery, "hello2">;

import gql from "graphql-tag";
import * as React from "react";
import * as ReactApollo from "react-apollo";
import * as ReactApolloHooks from "react-apollo-hooks";
export type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>>;

export const GetHello2Document = gql`
  query getHello2 {
    hello2(input: { name: "Hong" })
  }
`;

export const GetHello2Component = (
  props: Omit<
    Omit<
      ReactApollo.QueryProps<IGetHello2Query, IGetHello2QueryVariables>,
      "query"
    >,
    "variables"
  > & { variables?: IGetHello2QueryVariables }
) => (
  <ReactApollo.Query<IGetHello2Query, IGetHello2QueryVariables>
    query={GetHello2Document}
    {...props}
  />
);

export type IGetHello2Props<TChildProps = {}> = Partial<
  ReactApollo.DataProps<IGetHello2Query, IGetHello2QueryVariables>
> &
  TChildProps;
export function withGetHello2<TProps, TChildProps = {}>(
  operationOptions?: ReactApollo.OperationOption<
    TProps,
    IGetHello2Query,
    IGetHello2QueryVariables,
    IGetHello2Props<TChildProps>
  >
) {
  return ReactApollo.withQuery<
    TProps,
    IGetHello2Query,
    IGetHello2QueryVariables,
    IGetHello2Props<TChildProps>
  >(GetHello2Document, {
    alias: "withGetHello2",
    ...operationOptions
  });
}

export function useGetHello2Query(
  baseOptions?: ReactApolloHooks.QueryHookOptions<IGetHello2QueryVariables>
) {
  return ReactApolloHooks.useQuery<IGetHello2Query, IGetHello2QueryVariables>(
    GetHello2Document,
    baseOptions
  );
}
