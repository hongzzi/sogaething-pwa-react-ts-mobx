import { Request, Response } from 'express-serve-static-core';
import cookie from 'js-cookie';
import forIn from 'lodash/forIn';
import { IToken } from './../store/AuthStore';

export const setCookie = (key: string, value: string) => {
  if (process.browser) {
    cookie.set(key, value, {
      expires: 1000000,
      // path: '/',
    });
  }
};

export const getCookie = (key: string, req: Request) => {
  return process.browser
    ? getCookieFromBrowser(key)
    : getCookieFromServer(key, req);
};

export function setMapInCookie(res: Response, token: string): void {
  const cookies = '';

  // forIn(map, (value, key) => {
  //   cookies += `${key}=${value}; `
  // })
  // console.log('-----------------setMapInCookie---------------------');
  // console.log(token);
  // console.log('-----------------setMapInCookieEnd---------------------');
  res.setHeader('Set-Cookie', 'token=' + token + '; ' + ' Secure; HttpOnly;');
}

export function removeMapInCookie(keys: string[], res: Response): void {
  let cookies = '';

  keys.map((key) => {
    cookies += `${key}=deleted; `;
  });

  res.setHeader('Set-Cookie', cookies + 'Path=/; Secure; HttpOnly;');
}

const getCookieFromBrowser = (key: string) => {
  // console.log('----------------getCookieFormBrowser----------------');
  // console.log(cookie.get(key));
  // console.log('----------------getCookieFormBrowserEnd----------------');
  return cookie.get(key);
};

const getCookieFromServer = (key: string, req: Request) => {
  if (!req.headers.cookie) {
    return undefined;
  }
  const rawCookie = req.headers.cookie
    .split(';')
    .find((c) => c.trim().startsWith(`${key}=`));
  if (!rawCookie) {
    return undefined;
  }
  // console.log('----------------getCookieFormServer----------------');
  // console.log(rawCookie.split('=')[1]);
  // console.log('----------------getCookieFormServer----------------');
  return rawCookie.split('=')[1];
};
