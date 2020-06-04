import { Response } from 'express-serve-static-core'
import forIn from 'lodash/forIn'
import { IToken } from './../store/AuthStore';

export function setMapInCookie(res: Response, map: { [key: string]: string } | IToken): void {
  let cookies = ''

  forIn(map, (value, key) => {
    cookies += `${key}=${value}; `
  })

  res.setHeader('Set-Cookie', cookies + 'Path=/; Secure; HttpOnly;')
}

export function removeMapInCookie(keys: string[], res: Response): void {
  let cookies = ''

  keys.map((key) => {
    cookies += `${key}=deleted; `
  })

  res.setHeader('Set-Cookie', cookies + 'Path=/; Secure; HttpOnly;')
}
