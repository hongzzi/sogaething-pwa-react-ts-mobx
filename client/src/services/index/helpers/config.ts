import * as dotenv from 'dotenv';

dotenv.config();
let path;
switch (process.env.NODE_ENV) {
  case 'test':
    path = `${__dirname}/../../.env.test`;
    break;
  case 'production':
    path = `${__dirname}/../../.env.production`;
    break;
  default:
    path = `${__dirname}/../../.env.development`;
}
dotenv.config({ path });

export const NEXT_APP_STAGE = process.env.NEXT_APP_STAGE;
export const NEXT_APP_REST_ENDPOINT = process.env.NEXT_APP_REST_ENDPOINT;
export const NEXT_APP_GRAPHQL_ENDPOINT = process.env.NEXT_APP_GRAPHQL_ENDPOINT;
export const NEXT_APP_KAKAO_CLIENT_KEY = process.env.NEXT_APP_KAKAO_CLIENT_KEY;
export const NEXT_APP_SOCKET_ENDPOINT = process.env.NEXT_APP_SOCKET_ENDPOINT;
