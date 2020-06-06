import Document, { Head, Main, NextScript } from 'next/document'
import { ServerStyleSheet } from 'styled-components'

export default class extends Document {
  static async getInitialProps(context: any) {
    const sheet = new ServerStyleSheet()
    const { renderPage: h } = context

    context.renderPage = () =>
      h({
        enhanceApp: (App: any) => (props: any) =>
          sheet.collectStyles(<App {...props} />),
      })

    const initialProps = await Document.getInitialProps(context)

    return {
      ...initialProps,
      styles: sheet.getStyleElement(),
    }
  }

  render() {
    return (
      <html>
        <Head>
          <meta name='apple-mobile-web-app-capable' content='yes' />
          <meta name='apple-mobile-web-app-status-bar-style' content='black' />
          <link
            rel='apple-touch-icon'
            sizes='72x72'
            href='/apple-touch-icon-72x72.png'
          />
          <link
            rel='apple-touch-icon'
            sizes='114x114'
            href='/apple-touch-icon-114x114.png'
          />
          <link rel='apple-touch-icon' href='/apple-touch-icon-57x57.png' />
          <link rel='apple-touch-startup-image' href='/splash-startup.png' />
          <meta name='mobile-web-app-capable' content='yes' />
          <link rel='icon' sizes='72x72'href='/icon72.png'/>
          <link rel='icon' sizes='114x114'href='/icon114.png'/>
          <link rel='icon' sizes='192x192'href='/icon.png' />
          <link rel='icon' sizes='57x57'href='/icon57.png' />

          {/* will be update manifest.json url  */}
          <link rel='manifest' href='/static/manifest.json' />
          <link href='https://fonts.googleapis.com/css2?family=Jua&display=swap' rel='stylesheet'/>
        </Head>
        <body>
          <Main />
          <NextScript />
        </body>
      </html>
    )
  }
}
