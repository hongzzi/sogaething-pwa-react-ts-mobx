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
};

export type ICreateFileInput = {
  productId: Scalars["Int"];
  imgPath: Scalars["String"];
};

export type ICreateHashtagInput = {
  productId: Scalars["Int"];
  hashtag: Scalars["String"];
};

export type ICreatePostInput = {
  title: Scalars["String"];
  contents: Scalars["String"];
  deal: Scalars["String"];
  /** dealLocation:String! */
  category: Scalars["String"];
  productname: Scalars["String"];
  /** productState : String! */
  price: Scalars["Int"];
  hashtag: Scalars["String"];
  imgPaths: Scalars["String"];
};

export type ICreateProductInput = {
  postId: Scalars["Int"];
  price: Scalars["Int"];
  name: Scalars["String"];
  category: Scalars["String"];
};

export type IDetailDeal = {
  dealId: Scalars["ID"];
  post: IPost;
  user: IUser;
  createdDate?: Maybe<Scalars["String"]>;
  modifiedDate?: Maybe<Scalars["String"]>;
};

export type IDetailOutput = {
  dealId: Scalars["ID"];
  postId: Scalars["Int"];
  imgPaths: Array<Maybe<IFile>>;
  title: Scalars["String"];
  category: Scalars["String"];
  hashtag: Array<Maybe<IHashtag>>;
  contents: Scalars["String"];
  price: Scalars["Int"];
  buyerId: Scalars["Int"];
  sellerId: Scalars["Int"];
  user: IUserInfoResponse;
};

export type IFile = {
  fileId: Scalars["ID"];
  product: IProduct;
  imgPath: Scalars["String"];
};

export type IFileArr = {
  imgPath: Scalars["String"];
};

export type IFileOutput = {
  fileId: Scalars["Int"];
  productId: Scalars["Int"];
  imgPath: Scalars["String"];
};

export type IHashtag = {
  hashtagId: Scalars["ID"];
  product: IProduct;
  hashtag: Scalars["String"];
};

export type IHashtagOutput = {
  hashtagId: Scalars["ID"];
  productId: Scalars["Int"];
  hashtag: Scalars["String"];
};

export type IHistoryOutput = {
  userId?: Maybe<Scalars["Int"]>;
  postId?: Maybe<Scalars["Int"]>;
  createdDate?: Maybe<Scalars["String"]>;
  modifiedDate?: Maybe<Scalars["String"]>;
};

export type ILoginUserInput = {
  provider: Scalars["String"];
  token: Scalars["String"];
};

export type ILoginUserOutput = {
  token: Scalars["String"];
};

export type IMutation = {
  updateProduct?: Maybe<IProductOutput>;
  createProduct?: Maybe<IProductOutput>;
  deleteProduct: Scalars["Int"];
  createFile?: Maybe<IFileOutput>;
  updateFile?: Maybe<IFileOutput>;
  deleteFile: Scalars["Int"];
  createDetailDeal?: Maybe<IDetailOutput>;
  deleteDetailDeal: Scalars["Int"];
  loginUser?: Maybe<ILoginUserOutput>;
  updateUser?: Maybe<IUserOutput>;
  deleteUser?: Maybe<Scalars["Int"]>;
  createHashtag?: Maybe<IHashtagOutput>;
  updateHashtag?: Maybe<IHashtagOutput>;
  deleteHashtag?: Maybe<Scalars["Int"]>;
  updateViewcount: Scalars["Int"];
  updateIsBuy: Scalars["Int"];
  createPost?: Maybe<IPostOutput>;
  updatePost?: Maybe<IPostOutput>;
  deletePost: Scalars["Int"];
  createHistory?: Maybe<IHistoryOutput>;
};

export type IMutationUpdateProductArgs = {
  input: IUpdateProductInput;
};

export type IMutationCreateProductArgs = {
  input: ICreateProductInput;
};

export type IMutationDeleteProductArgs = {
  id: Scalars["Int"];
};

export type IMutationCreateFileArgs = {
  input: ICreateFileInput;
};

export type IMutationUpdateFileArgs = {
  input: IUpdateFileInput;
};

export type IMutationDeleteFileArgs = {
  id: Scalars["Int"];
};

export type IMutationCreateDetailDealArgs = {
  input: ICreateDetailDealInput;
};

export type IMutationDeleteDetailDealArgs = {
  id: Scalars["Int"];
};

export type IMutationLoginUserArgs = {
  input: ILoginUserInput;
};

export type IMutationUpdateUserArgs = {
  input: IUpdateUserInput;
};

export type IMutationDeleteUserArgs = {
  id: Scalars["Int"];
};

export type IMutationCreateHashtagArgs = {
  input: ICreateHashtagInput;
};

export type IMutationUpdateHashtagArgs = {
  input: IUpdateHashtagInput;
};

export type IMutationDeleteHashtagArgs = {
  id: Scalars["Int"];
};

export type IMutationUpdateViewcountArgs = {
  postId: Scalars["Int"];
};

export type IMutationUpdateIsBuyArgs = {
  postId: Scalars["Int"];
};

export type IMutationCreatePostArgs = {
  input: ICreatePostInput;
};

export type IMutationUpdatePostArgs = {
  input: IUpdatePostInput;
};

export type IMutationDeletePostArgs = {
  postId: Scalars["Int"];
};

export type IMutationCreateHistoryArgs = {
  postId: Scalars["Int"];
};

export type IPost = {
  postId: Scalars["ID"];
  userId?: Maybe<Scalars["Int"]>;
  isBuy: Scalars["Boolean"];
  title: Scalars["String"];
  saleDate: Scalars["String"];
  contents: Scalars["String"];
  viewCount?: Maybe<Scalars["Int"]>;
  deal: Scalars["String"];
  dealState: Scalars["String"];
  createdDate?: Maybe<Scalars["String"]>;
  modifiedDate?: Maybe<Scalars["String"]>;
};

export type IPostOutput = {
  postId: Scalars["ID"];
  userId: Scalars["Int"];
  /** 게시자 id */
  isBuy: Scalars["Boolean"];
  /** 구매 or 판매 */
  title: Scalars["String"];
  contents: Scalars["String"];
  deal: Scalars["String"];
  dealState: Scalars["String"];
  category: Scalars["String"];
  name: Scalars["String"];
  hashtag: Scalars["String"];
  imgPaths?: Maybe<Array<IFileArr>>;
};

export type IProduct = {
  productId: Scalars["ID"];
  post: IPost;
  name: Scalars["String"];
  price: Scalars["Int"];
  category: Scalars["String"];
};

export type IProductOutput = {
  productId: Scalars["ID"];
  postId: Scalars["Int"];
  name: Scalars["String"];
  price: Scalars["Int"];
  category: Scalars["String"];
};

export type IQuery = {
  findAllPosts?: Maybe<Array<Maybe<IPost>>>;
  findAllPost?: Maybe<Array<Maybe<IPostOutput>>>;
  findPostByPostId?: Maybe<IPostOutput>;
  /** findAllPostsByUploaderId(uploader_id: Int):[Post]
   *    findAllPostsByUploaderId(uploader_id: Int):[Post]
   *    findPostByPostId(id: Int): Post
   */
  findRecentPosts?: Maybe<Array<Maybe<IRecentPostResponse>>>;
  findAllFile?: Maybe<Array<Maybe<IFileOutput>>>;
  findAllFiles?: Maybe<Array<Maybe<IFile>>>;
  findFileById?: Maybe<IFileOutput>;
  findAllDetailDeals?: Maybe<Array<Maybe<IDetailDeal>>>;
  findAllDetailDeal?: Maybe<Array<Maybe<IDetailOutput>>>;
  findDetailDealByPost?: Maybe<IDetailOutput>;
  findAllUsers?: Maybe<Array<Maybe<IUser>>>;
  findAllUser?: Maybe<Array<Maybe<IUserOutput>>>;
  findUserInfo?: Maybe<IUserInfoResponse>;
  findAllHashtags?: Maybe<Array<Maybe<IHashtag>>>;
  findAllHashtag?: Maybe<Array<Maybe<IHashtagOutput>>>;
  findByHashtagId?: Maybe<IHashtagOutput>;
  findAllProduct?: Maybe<Array<Maybe<IProductOutput>>>;
  findAllProducts?: Maybe<Array<Maybe<IProduct>>>;
  findByProductId?: Maybe<IProductOutput>;
  findUserHistoryByUserId?: Maybe<Array<Maybe<IUserHistoryResponse>>>;
};

export type IQueryFindPostByPostIdArgs = {
  id?: Maybe<Scalars["Int"]>;
};

export type IQueryFindFileByIdArgs = {
  id?: Maybe<Scalars["Int"]>;
};

export type IQueryFindDetailDealByPostArgs = {
  postId?: Maybe<Scalars["Int"]>;
};

export type IQueryFindByHashtagIdArgs = {
  id?: Maybe<Scalars["Int"]>;
};

export type IQueryFindByProductIdArgs = {
  id?: Maybe<Scalars["Int"]>;
};

export type IRecentPostResponse = {
  postId: Scalars["String"];
  user: IUser;
  hashTags: Array<Maybe<IHashtag>>;
  isBuy: Scalars["Boolean"];
  price: Scalars["Int"];
  saleDate: Scalars["String"];
  imgUrls: Array<Maybe<IFile>>;
  category: Scalars["String"];
  deal: Scalars["String"];
  createdDate?: Maybe<Scalars["String"]>;
  modifiedDate?: Maybe<Scalars["String"]>;
};

export type IUpdateFileInput = {
  fileId: Scalars["Int"];
  imgPath: Scalars["String"];
};

export type IUpdateHashtagInput = {
  hashtagId: Scalars["Int"];
  hashtag: Scalars["String"];
};

export type IUpdatePostInput = {
  postId: Scalars["Int"];
  title: Scalars["String"];
  contents: Scalars["String"];
  deal: Scalars["String"];
  dealState: Scalars["String"];
  category: Scalars["String"];
  productname: Scalars["String"];
  price: Scalars["Int"];
  hashtag: Scalars["String"];
  imgPaths: Scalars["String"];
};

export type IUpdateProductInput = {
  productId: Scalars["ID"];
  postId: Scalars["Int"];
  price: Scalars["Int"];
  name: Scalars["String"];
  category: Scalars["String"];
};

export type IUpdateUserInput = {
  imageUrl?: Maybe<Scalars["String"]>;
  phone: Scalars["String"];
  address: Scalars["String"];
  trust: Scalars["Int"];
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

export type IUserHistoryResponse = {
  user: IUser;
  postId: Scalars["ID"];
  isBuy: Scalars["Boolean"];
  title: Scalars["String"];
  saleDate: Scalars["String"];
  contents: Scalars["String"];
  viewCount?: Maybe<Scalars["Int"]>;
  deal: Scalars["String"];
  createdDate?: Maybe<Scalars["String"]>;
  modifiedDate?: Maybe<Scalars["String"]>;
  hashTags: Array<Maybe<IHashtag>>;
  price: Scalars["Int"];
  imgUrls: Array<Maybe<IFile>>;
};

export type IUserInfoResponse = {
  name?: Maybe<Scalars["String"]>;
  address?: Maybe<Scalars["String"]>;
  trust?: Maybe<Scalars["Int"]>;
  numOfPosts?: Maybe<Scalars["Int"]>;
  imgurl?: Maybe<Scalars["String"]>;
};

export type IUserOutput = {
  userId: Scalars["ID"];
  name: Scalars["String"];
  email?: Maybe<Scalars["String"]>;
  imageUrl?: Maybe<Scalars["String"]>;
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

export type IGetUserInfoQueryVariables = {};

export type IGetUserInfoQuery = { __typename?: "Query" } & {
  findUserInfo: Maybe<
    { __typename?: "UserInfoResponse" } & Pick<
      IUserInfoResponse,
      "name" | "address" | "trust" | "numOfPosts" | "imgurl"
    >
  >;
};

export type IGetHistoryQueryVariables = {};

export type IGetHistoryQuery = { __typename?: "Query" } & {
  findUserHistoryByUserId: Maybe<
    Array<
      Maybe<
        { __typename?: "UserHistoryResponse" } & Pick<
          IUserHistoryResponse,
          | "postId"
          | "isBuy"
          | "title"
          | "saleDate"
          | "contents"
          | "viewCount"
          | "deal"
          | "createdDate"
          | "modifiedDate"
          | "price"
        > & {
            user: { __typename?: "User" } & Pick<
              IUser,
              | "userId"
              | "name"
              | "email"
              | "imageUrl"
              | "provider"
              | "providerId"
              | "phone"
              | "address"
              | "trust"
            >;
            hashTags: Array<
              Maybe<
                { __typename?: "Hashtag" } & Pick<
                  IHashtag,
                  "hashtagId" | "hashtag"
                > & {
                    product: { __typename?: "Product" } & Pick<
                      IProduct,
                      "productId" | "name" | "price" | "category"
                    > & {
                        post: { __typename?: "Post" } & Pick<
                          IPost,
                          | "postId"
                          | "userId"
                          | "isBuy"
                          | "title"
                          | "saleDate"
                          | "contents"
                          | "viewCount"
                          | "deal"
                          | "dealState"
                          | "createdDate"
                          | "modifiedDate"
                        >;
                      };
                  }
              >
            >;
            imgUrls: Array<
              Maybe<
                { __typename?: "File" } & Pick<IFile, "fileId" | "imgPath"> & {
                    product: { __typename?: "Product" } & Pick<
                      IProduct,
                      "productId" | "name" | "price" | "category"
                    > & {
                        post: { __typename?: "Post" } & Pick<
                          IPost,
                          | "postId"
                          | "userId"
                          | "isBuy"
                          | "title"
                          | "saleDate"
                          | "contents"
                          | "viewCount"
                          | "deal"
                          | "dealState"
                          | "createdDate"
                          | "modifiedDate"
                        >;
                      };
                  }
              >
            >;
          }
      >
    >
  >;
};

export type IGetFindRecentPostsQueryVariables = {};

export type IGetFindRecentPostsQuery = { __typename?: "Query" } & {
  findRecentPosts: Maybe<
    Array<
      Maybe<
        { __typename?: "RecentPostResponse" } & Pick<
          IRecentPostResponse,
          | "postId"
          | "isBuy"
          | "price"
          | "saleDate"
          | "category"
          | "deal"
          | "createdDate"
          | "modifiedDate"
        > & {
            user: { __typename?: "User" } & Pick<
              IUser,
              | "userId"
              | "name"
              | "email"
              | "imageUrl"
              | "provider"
              | "providerId"
              | "phone"
              | "address"
              | "trust"
            >;
            hashTags: Array<
              Maybe<
                { __typename?: "Hashtag" } & Pick<
                  IHashtag,
                  "hashtagId" | "hashtag"
                > & {
                    product: { __typename?: "Product" } & Pick<
                      IProduct,
                      "productId" | "name" | "price" | "category"
                    > & {
                        post: { __typename?: "Post" } & Pick<
                          IPost,
                          | "postId"
                          | "userId"
                          | "isBuy"
                          | "title"
                          | "saleDate"
                          | "contents"
                          | "viewCount"
                          | "deal"
                          | "dealState"
                          | "createdDate"
                          | "modifiedDate"
                        >;
                      };
                  }
              >
            >;
            imgUrls: Array<
              Maybe<
                { __typename?: "File" } & Pick<IFile, "fileId" | "imgPath"> & {
                    product: { __typename?: "Product" } & Pick<
                      IProduct,
                      "productId" | "name" | "price" | "category"
                    > & {
                        post: { __typename?: "Post" } & Pick<
                          IPost,
                          | "postId"
                          | "userId"
                          | "isBuy"
                          | "title"
                          | "saleDate"
                          | "contents"
                          | "viewCount"
                          | "deal"
                          | "dealState"
                          | "createdDate"
                          | "modifiedDate"
                        >;
                      };
                  }
              >
            >;
          }
      >
    >
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
export const GetUserInfoDocument = gql`
  query getUserInfo {
    findUserInfo {
      name
      address
      trust
      numOfPosts
      imgurl
    }
  }
`;

export const GetUserInfoComponent = (
  props: Omit<
    Omit<
      ReactApollo.QueryProps<IGetUserInfoQuery, IGetUserInfoQueryVariables>,
      "query"
    >,
    "variables"
  > & { variables?: IGetUserInfoQueryVariables }
) => (
  <ReactApollo.Query<IGetUserInfoQuery, IGetUserInfoQueryVariables>
    query={GetUserInfoDocument}
    {...props}
  />
);

export type IGetUserInfoProps<TChildProps = {}> = Partial<
  ReactApollo.DataProps<IGetUserInfoQuery, IGetUserInfoQueryVariables>
> &
  TChildProps;
export function withGetUserInfo<TProps, TChildProps = {}>(
  operationOptions?: ReactApollo.OperationOption<
    TProps,
    IGetUserInfoQuery,
    IGetUserInfoQueryVariables,
    IGetUserInfoProps<TChildProps>
  >
) {
  return ReactApollo.withQuery<
    TProps,
    IGetUserInfoQuery,
    IGetUserInfoQueryVariables,
    IGetUserInfoProps<TChildProps>
  >(GetUserInfoDocument, {
    alias: "withGetUserInfo",
    ...operationOptions
  });
}

export function useGetUserInfoQuery(
  baseOptions?: ReactApolloHooks.QueryHookOptions<IGetUserInfoQueryVariables>
) {
  return ReactApolloHooks.useQuery<
    IGetUserInfoQuery,
    IGetUserInfoQueryVariables
  >(GetUserInfoDocument, baseOptions);
}
export const GetHistoryDocument = gql`
  query getHistory {
    findUserHistoryByUserId {
      user {
        userId
        name
        email
        imageUrl
        provider
        providerId
        phone
        address
        trust
      }
      postId
      isBuy
      title
      saleDate
      contents
      viewCount
      deal
      createdDate
      modifiedDate
      hashTags {
        hashtagId
        product {
          productId
          post {
            postId
            userId
            isBuy
            title
            saleDate
            contents
            viewCount
            deal
            dealState
            createdDate
            modifiedDate
          }
          name
          price
          category
        }
        hashtag
      }
      price
      imgUrls {
        fileId
        product {
          productId
          post {
            postId
            userId
            isBuy
            title
            saleDate
            contents
            viewCount
            deal
            dealState
            createdDate
            modifiedDate
          }
          name
          price
          category
        }
        imgPath
      }
    }
  }
`;

export const GetHistoryComponent = (
  props: Omit<
    Omit<
      ReactApollo.QueryProps<IGetHistoryQuery, IGetHistoryQueryVariables>,
      "query"
    >,
    "variables"
  > & { variables?: IGetHistoryQueryVariables }
) => (
  <ReactApollo.Query<IGetHistoryQuery, IGetHistoryQueryVariables>
    query={GetHistoryDocument}
    {...props}
  />
);

export type IGetHistoryProps<TChildProps = {}> = Partial<
  ReactApollo.DataProps<IGetHistoryQuery, IGetHistoryQueryVariables>
> &
  TChildProps;
export function withGetHistory<TProps, TChildProps = {}>(
  operationOptions?: ReactApollo.OperationOption<
    TProps,
    IGetHistoryQuery,
    IGetHistoryQueryVariables,
    IGetHistoryProps<TChildProps>
  >
) {
  return ReactApollo.withQuery<
    TProps,
    IGetHistoryQuery,
    IGetHistoryQueryVariables,
    IGetHistoryProps<TChildProps>
  >(GetHistoryDocument, {
    alias: "withGetHistory",
    ...operationOptions
  });
}

export function useGetHistoryQuery(
  baseOptions?: ReactApolloHooks.QueryHookOptions<IGetHistoryQueryVariables>
) {
  return ReactApolloHooks.useQuery<IGetHistoryQuery, IGetHistoryQueryVariables>(
    GetHistoryDocument,
    baseOptions
  );
}
export const GetFindRecentPostsDocument = gql`
  query getFindRecentPosts {
    findRecentPosts {
      postId
      user {
        userId
        name
        email
        imageUrl
        provider
        providerId
        phone
        address
        trust
      }
      hashTags {
        hashtagId
        product {
          productId
          post {
            postId
            userId
            isBuy
            title
            saleDate
            contents
            viewCount
            deal
            dealState
            createdDate
            modifiedDate
          }
          name
          price
          category
        }
        hashtag
      }
      isBuy
      price
      saleDate
      imgUrls {
        fileId
        product {
          productId
          post {
            postId
            userId
            isBuy
            title
            saleDate
            contents
            viewCount
            deal
            dealState
            createdDate
            modifiedDate
          }
          name
          price
          category
        }
        imgPath
      }
      category
      deal
      createdDate
      modifiedDate
    }
  }
`;

export const GetFindRecentPostsComponent = (
  props: Omit<
    Omit<
      ReactApollo.QueryProps<
        IGetFindRecentPostsQuery,
        IGetFindRecentPostsQueryVariables
      >,
      "query"
    >,
    "variables"
  > & { variables?: IGetFindRecentPostsQueryVariables }
) => (
  <ReactApollo.Query<
    IGetFindRecentPostsQuery,
    IGetFindRecentPostsQueryVariables
  >
    query={GetFindRecentPostsDocument}
    {...props}
  />
);

export type IGetFindRecentPostsProps<TChildProps = {}> = Partial<
  ReactApollo.DataProps<
    IGetFindRecentPostsQuery,
    IGetFindRecentPostsQueryVariables
  >
> &
  TChildProps;
export function withGetFindRecentPosts<TProps, TChildProps = {}>(
  operationOptions?: ReactApollo.OperationOption<
    TProps,
    IGetFindRecentPostsQuery,
    IGetFindRecentPostsQueryVariables,
    IGetFindRecentPostsProps<TChildProps>
  >
) {
  return ReactApollo.withQuery<
    TProps,
    IGetFindRecentPostsQuery,
    IGetFindRecentPostsQueryVariables,
    IGetFindRecentPostsProps<TChildProps>
  >(GetFindRecentPostsDocument, {
    alias: "withGetFindRecentPosts",
    ...operationOptions
  });
}

export function useGetFindRecentPostsQuery(
  baseOptions?: ReactApolloHooks.QueryHookOptions<
    IGetFindRecentPostsQueryVariables
  >
) {
  return ReactApolloHooks.useQuery<
    IGetFindRecentPostsQuery,
    IGetFindRecentPostsQueryVariables
  >(GetFindRecentPostsDocument, baseOptions);
}
