import { createGlobalStyle } from './';

export default createGlobalStyle`
  @import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
  * {
    @font-face { font-family: 'GmarketSansMedium'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff'); font-weight: normal; font-style: normal; }
    box-sizing: border-box;
    font-family: 'Noto Sans KR', -apple-system, BlinkMacSystemFont, 'Malgun Gothic', 'Segoe UI', Roboto, Helvetica, Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
    @font-face { font-family: 'TmonMonsori'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/TmonMonsori.woff') format('woff'); font-weight: normal; font-style: normal; }

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

  input[type="color"],
  input[type="date"],
  input[type="datetime"],
  input[type="datetime-local"],
  input[type="email"],
  input[type="month"],
  input[type="number"],
  input[type="password"],
  input[type="search"],
  input[type="tel"],
  input[type="text"],
  input[type="time"],
  input[type="url"],
  input[type="week"],
  select:focus,
  textarea {
    font-size: 16px;
  }

  @media screen and (-webkit-min-device-pixel-ratio:0) {
    select:focus,
    textarea:focus,
    input:focus {
      font-size: 16px;
      background: #eee;
    }
  }

  @media screen and (-webkit-min-device-pixel-ratio:0) {
    select,
    textarea,
    input {
      font-size: 16px;
    }
  }
`;
