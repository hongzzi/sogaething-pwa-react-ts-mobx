export enum STORES {
    AUTH_STORE = 'authStore',
    COUNTER_STORE = 'counterStore',
  }

export enum PAGE_PATHS {
    SIGNUP = '/signup',
    SIGNIN = '/signin',
    COUNTER = '/counter',
    PRIVATE = '/private',
  }

export enum ENDPOINT {
    //배포용
    GRAPHQL = 'https://k02a4041.p.ssafy.io/graphql',
    REST = 'https://k02a4041.p.ssafy.io/chat',
    SOCKET = 'https://k02a4041.p.ssafy.io/ws-stomp',

    // GRAPHQL = 'https://www.sogaething.com/graphql',
    // REST = 'https://www.sogaething.com/chat',
    // SOCKET = 'https://www.sogaething.com/ws-stomp',

    // 로컬
    // GRAPHQL = 'http://localhost:8080/graphql',
    // REST = 'https://k02a4041.p.ssafy.io/chat',
    // SOCKET = 'https://k02a4041.p.ssafy.io/ws-stomp',

    // GRAPHQL = 'http://localhost:8081/graphql',
    // REST = 'http://localhost:8081/chat',
    // SOCKET = 'http://localhost:8081/ws-stomp',
}

export enum KEYS {
  KAKAO = 'bab75ca79827f0172aca13ffe4fff7f5',
  TOSS = '8b7c1a0df2354665934f6deb01f9ea0e',
}
