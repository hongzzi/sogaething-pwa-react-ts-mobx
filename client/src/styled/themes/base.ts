import openColor from 'open-color';

const theme = {
  ...openColor,
  pointFontColor: '#259be5',
  // pointFontColor: '#f7b500',
  prevColor: '#ffaa00',
  mainFontColor: '#2b2b2b',
  mainBGcolor: '#ffffff',
  mainCategoryTextColor: '#08979c',
  subFontColor: '#c6c6c6',
  bolderColor: '#c6c6c6',
  searchBarColor: '#259be5',
  purpleColor: '#6459db',
  mainUserCardBGColor: '#ffffff',
  button: {
    primary: {
      bodyColor: '#6459db',
      borderColor: '#6459db',
      color: '#ffffff',
    },
    common: {
      bodyColor: '#259be5',
      borderColor: '#259be5',
      color: '#ffffff',
    },
    disable: {
      bodyColor: '#f6f7f9',
      borderColor: '#8f9bb3',
      color: '#8f9bb3',
    },
    login: {
      kakao: { bg: '#ffffff', border: '#ffe812' },
      google: { bg: '#ffffff', border: '#ff6b6b'},
      naver: { bg: '#5dcb50', border: 'none' },
      facebook: { bg: '#ffffff', border: '#0091ff' },
    },
    chatting: {
      bodyColor: '#ff3d71',
      borderColor: '#ff3d71',
      color: '#fff',
    },
  },
};

export default theme;
export type Theme = typeof theme;
