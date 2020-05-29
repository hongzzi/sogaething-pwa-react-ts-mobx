import { createGlobalStyle } from './'

export default createGlobalStyle`
  @import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
  * {
    @font-face { font-family: 'GmarketSansMedium'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff'); font-weight: normal; font-style: normal; }
    box-sizing: border-box;
    font-family: 'Noto Sans KR','Jua', -apple-system, BlinkMacSystemFont, 'Malgun Gothic', 'Segoe UI', Roboto, Helvetica, Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
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
