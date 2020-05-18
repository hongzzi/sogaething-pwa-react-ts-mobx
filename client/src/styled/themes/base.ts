import openColor from 'open-color';

const theme = {
  ...openColor,
  pointFontColor : '#f7b500',
  mainFontColor : '#2b2b2b',
  mainBGcolor: '#ffffff',
  mainCategoryTextColor: '#08979c',
  subFontColor : '#c6c6c6',
  searchBarColor: '#ff9500',
  
  mainUserCardBGColor: '#222b45',
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
  },
};

export default theme;
export type Theme = typeof theme;
