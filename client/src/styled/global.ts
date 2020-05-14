import { createGlobalStyle } from './'

export default createGlobalStyle`
  * {
    box-sizing: border-box;
    font-family: -apple-system, BlinkMacSystemFont, 'Malgun Gothic', 'Segoe UI', Roboto, Helvetica, Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
  }

  html {
    font-size: 16px;
    height: 100%;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  body {
    padding: 0;
    margin: 0;
    height: 100%;
  }

  #__next {
    height: 100%;
  }
`
