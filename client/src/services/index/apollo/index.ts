import { InMemoryCache, NormalizedCacheObject } from 'apollo-cache-inmemory'
import { ApolloClient } from 'apollo-client'
import { setContext } from 'apollo-link-context'
import { createHttpLink } from 'apollo-link-http'
import fetch from 'isomorphic-unfetch'
import { checkTokenIsExpired } from '../helpers'
import { ENDPOINT } from './../constants';
import { NEXT_APP_GRAPHQL_ENDPOINT } from './../helpers/config';
import { IToken } from './../store/AuthStore';
import { RootStore } from './../store/index';

let apolloClient: ApolloClient<NormalizedCacheObject>

const isServer = typeof window === 'undefined'

export function createApolloClient(store: RootStore, state?: any) {
  if (apolloClient) {
    return apolloClient

  } else {
    const httpLink = createHttpLink({
      fetch,
      uri: ENDPOINT.GRAPHQL,
      credentials: 'include', // 서버로 부터 http only 쿠키 저장 할 수게 하는 옵션
    })

    const link = createAuthorizationLink(store).concat(httpLink)

    const cache = new InMemoryCache()

    cache.restore(state || {})

    if (isServer) {
      return new ApolloClient({
        cache,
        link,
        ssrMode: true,
      })

    } else {
      return apolloClient = new ApolloClient({
        cache,
        connectToDevTools: true,
        link,
      })
    }
  }
}

function createAuthorizationLink(store: RootStore) {
  return setContext(async (_req, { headers }) => {
    const token: IToken = {
      token : store.authStore.token,
    }
    if (typeof token === 'undefined') {
      return {
        headers,
      }
    }

    const isStoredAccessTokenExpired = checkTokenIsExpired(token.token)

    if (!isStoredAccessTokenExpired) {
      return createHeaders(token.token, headers)

    } else {
      try {
        const { token: refreshedAccessToken } = await store.authStore.refreshTokens(token)

        if (!refreshedAccessToken) {
          throw new Error()
        }

        return createHeaders(refreshedAccessToken, headers)

      } catch (error) {
        return {
          headers,
        }
      }
    }
  })
}

function createHeaders(token: string, oldHeaders: any) {
  return {
    headers: { Authorization: `Bearer ${token}`, ...oldHeaders },
  }
}
