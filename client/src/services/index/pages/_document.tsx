import Document, { Head, Main, NextScript } from 'next/document';
import { ServerStyleSheet } from 'styled-components';

export default class extends Document {
  static async getInitialProps(context: any) {
    const sheet = new ServerStyleSheet();
    const { renderPage: h } = context;

    context.renderPage = () =>
      h({
        enhanceApp: (App: any) => (props: any) =>
          sheet.collectStyles(<App {...props} />),
      });

    const initialProps = await Document.getInitialProps(context);

    return {
      ...initialProps,
      styles: sheet.getStyleElement(),
    };
  }

  render() {
    return (
      <html>
        <Head>
          <meta name='apple-mobile-web-app-capable' content='yes' />
          <meta name='apple-mobile-web-app-status-bar-style' content='black' />
          <meta
            name='viewport'
            content='initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width'
          />
          <meta name='apple-mobile-web-app-capable' content='yes' />
          <link
            rel='apple-touch-icon'
            href='https://i.ibb.co/qJgDKmx/app-icon-180.png'
          />

          <link
            rel='apple-touch-icon'
            sizes='180x180'
            href='https://i.ibb.co/qJgDKmx/app-icon-180.png'
          />
          <link rel='apple-touch-icon' href='/apple-touch-icon-57x57.png' />
          <link rel='apple-touch-startup-image' href='/splash-startup.png' />
          <meta name='mobile-web-app-capable' content='yes' />
          <link rel='icon' sizes='120x120' href='/static/app_icon_120.png' />
          <link rel='icon' sizes='180x180' href='/static/app_icon_180.png' />
          <link rel='icon' sizes='192x192' href='/static/app_icon_192.png' />
          <link rel='icon' sizes='310x310' href='/static/app_icon_310.png' />
          <link rel='icon' sizes='512x512' href='/static/app_icon_512.png' />

          {/* will be update manifest.json url  */}
          <link rel='manifest' href='/static/manifest.json' />
        </Head>
        <body>
          <Main />
          <NextScript />
        </body>
      </html>
    );
  }
}
