import openColor from 'open-color';

const theme = {
  ...openColor,
  pointFontColor: '#f7b500',
  mainFontColor: '#2b2b2b',
  mainBGcolor: '#ffffff',
  mainCategoryTextColor: '#08979c',
  subFontColor: '#c6c6c6',
  searchBarColor: '#259be5',
  mainUserCardBGColor: '#ffffff',
  button: {
    primary: {
      bodyColor: '#fef9f0',
      borderColor: '#ffaa00',
      color: '#ffaa00',
    },
    common: {
      bodyColor: '#fef9f0',
      borderColor: '#ffaa00',
      color: '#ffaa00',
    },
    disable: {
      bodyColor: '#f6f7f9',
      borderColor: '#8f9bb3',
      color: '#8f9bb3',
    },
    login: {
      kakao: { bg: '#ffe812', border: 'none' },
      google: { bg: '#ffffff', border: '#ff6b6b'},
      naver: { bg: '#5dcb50', border: 'none' },
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
