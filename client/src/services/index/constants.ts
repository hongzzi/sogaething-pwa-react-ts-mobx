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
    GRAPHQL = 'http://www.sogaething.com:8081/graphql',
    REST = 'http://www.sogaething.com:8081/chat',
    SOCKET = 'http://www.sogaething.com:8081/ws-stomp',

    // GRAPHQL = 'https://www.sogaething.com/graphql',
    // REST = 'https://www.sogaething.com/chat',
    // SOCKET = 'https://www.sogaething.com/ws-stomp',

    // GRAPHQL = 'http://localhost:8080/graphql',
    // REST = 'http://localhost:8080/chat',
    // SOCKET = 'http://localhost:8080/ws-stomp',

    // GRAPHQL = 'http://localhost:8081/graphql',
    // REST = 'http://localhost:8081/chat',
    // SOCKET = 'http://localhost:8081/ws-stomp',
}

export enum KEYS {
  KAKAO = 'bab75ca79827f0172aca13ffe4fff7f5',
}
