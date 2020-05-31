import ApolloClient from 'apollo-client';
import pickBy from 'lodash/pickBy';
import { Provider } from 'mobx-react';
import 'moment/locale/ko';
import { Container, default as NextApp } from 'next/app';
import Head from 'next/head';
import Router from 'next/router'
import React, { Fragment } from 'react';
import {
  ApolloProvider as ApolloHookProvider,
  getMarkupFromTree,
} from 'react-apollo-hooks';
import { renderToString as renderFunction } from 'react-dom/server';
import { ThemeProvider } from 'styled-components';
import GlobalStyle from '~/styled/global';
import baseTheme from '~/styled/themes/base';
import { createApolloClient } from '../apollo';
import FaviconImage from '../assets/favicon.png?url';
import initializeStore, { IEnvironments, RootStore } from '../store';
import {initialRoot } from '../store';

export default class extends React.Component {
  static async getInitialProps(appContext: any) {
    const appProps = await App.getInitialProps(appContext);
    const { Component, router, ctx } = appContext;

    let pageProps: {pageProps: any} = {pageProps : {}};
    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps(appContext);
    }

    const navState = getRouteNavIndex(router.asPath);
    const mobxStore = initializeStore({...initialRoot, pageStore: {
      clickedIdx: navState,
      nav: true,
    }});
    appContext.ctx.mobxStore = mobxStore;
    const apolloClient = createApolloClient(mobxStore);

    try {
      await getMarkupFromTree({
        tree: (
          <App
            Component={Component}
            router={router}
            apolloClient={apolloClient}
            store={mobxStore}
            {...appProps}
          />
        ),
        renderFunction,
      });
    } catch (error) {
      // tslint:disable-next-line:no-console
      // console.error(error);
      console.error('[Error 29948] Operating queries for SSR failed');
    }

    Head.rewind();

    return {
      apolloState: apolloClient.cache.extract(),
      mobxStore,
      ...appProps,
    };
  }

  apolloClient: ApolloClient<any>;
  store: RootStore;

  constructor(props: any) {
    super(props);
    const isServer = typeof window === 'undefined';
    this.store = isServer ? props.mobxStore : initializeStore(props.mobxStore);
    this.apolloClient = createApolloClient(this.store, props.apolloState);
  }

  render() {
    return (
      <App
        {...this.props}
        apolloClient={this.apolloClient}
        store={this.store}
      />
    );
  }
}

class App extends NextApp<any> {

  componentDidMount() {
    if (!window.sessionStorage.getItem('jwt')) {
      Router.push('/signin');
    }
  }

  render() {
    const { Component, pageProps } = this.props;

    return (
      <Container>
        <Head>
          <title>소개띵</title>
          <link rel='shortcut icon' href={FaviconImage} />
        </Head>
        <ApolloHookProvider client={this.props.apolloClient}>
          <Provider value={this.props.store}>
            <ThemeProvider theme={baseTheme}>
              <Fragment>
                <Component {...pageProps} />
                <GlobalStyle />
              </Fragment>
            </ThemeProvider>
          </Provider>
        </ApolloHookProvider>
        <noscript>You should use javascript</noscript>
      </Container>
    );
  }
}

function extractNextEnvironments(environments: IEnvironments): IEnvironments {
  return pickBy(environments, (_value, key) => key.indexOf('NEXT_APP') !== -1);
}

function getRouteNavIndex(path: string): number {
  if (path.startsWith('/chat')) {
    return 3;
  } else if (path.startsWith('/category')) {
    return 1;
  } else if (path.startsWith('/user')) {
    return 4;
  } else if (path.startsWith('')) {
    return 0;
  }
  return 0;
}
