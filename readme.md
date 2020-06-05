<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="README.md">
    <img src="img/main.png" alt="mock-up">
  </a>

  <h2 align="center">소개띵</h2>

  <p align="center">
    매칭시스템을 도입한 중고거래 PWA
    <br />
    <br />
    <a href="https://scene.zeplin.io/project/5ebe42c1266b3f485e753a12"><strong>UI/UX기획안</strong></a>
    · 
    <a href="https://docs.google.com/document/d/17E7PcYOrBnsgTW2I1TAsC5IN_dH2IjCPFka5-g5fVCs/edit#"><strong>프로젝트 계획서</strong></a>
</p>



<br>

<!-- ABOUT THE PROJECT -->
## :paperclip: 프로젝트 소개
> 검색하지 말고 소개받자, 물건을 소개해주는 소개 Thing !

![Product Name Screen Shot](img/작업화면.png)

소개띵은 조건검색으로 중고물품을 매칭시켜주는 중고거래 플랫폼입니다.

기존 중고거래 사이트 중고나라, 번개장터, 당근마켓은 많은 게시글을 제공해 이용자에게 결정권을 줍니다.
제목에 의존한 검색으로 년도, 모델명, 상태 등의 세부조건에 충족하는 물건을 찾는게 어렵습니다. 
그 중 원하는 가격과 조건의 물건을 찾으려면 많은 시간을 할애해야 합니다. 
또 마음에 드는 가격의 물품이라 연락했는데 사기글 또는 광고글이었던 경험은 누구나 가지고 있을 정도입니다. 

그래서 <b>소개띵</b>은 조건매칭시스템과 해시태그검색을 사용합니다. 
사용자가 원하는 조건에 가장 알맞은 게시글을 추천해주고 해시태그를 이용해 상세한 검색이 가능하도록 했습니다. 
추가로 카카오페이를 제공하여 은행앱에 가지 않아도 송금이 가능하도록 기능을 구현했기에 보다 쉬운 물품검색과 거래경험을 제공할 수 있습니다.

<br>

소개띵은 ```PWA(Progressive Web App)```입니다. <br>
> ```Cross-platform App```을 구축하는 기술 중 가장 인기있는 두 가지는 ```PWA```와 ```React Native``` 입니다. ```PWA를 선택한 이유```는 앱스토어를 통해 모바일 디바이스에 타겟팅 하는 것 외에 브라우저를 통해 광범위한 사용자 및 장치에 접근하고자 했기 때문입니다. PWA는 React Native에 비하여 비교적 적은 비용으로 다양한 기기에서 퍼포먼스를 낼 수 있습니다. HTML, CSS 및 JS를 사용하여 앱을 한 번 작성하면, 브라우저를 사용할 수 있는 대부분의 곳에서 실행됩니다. 브라우저를 사용할 수 없는 경우 PWA를 감싸고 있는 작은 화면에서 WebView를 사용하여 항상 하이브리드 앱을 빌드 할 수 있습니다.

참고문서 : [PWA vs React Native](https://www.kirupa.com/apps/pwa_vs_react_native.htm)

<br>


### :clipboard: 프로젝트 산출물

* [프로젝트 계획서](https://docs.google.com/document/d/17E7PcYOrBnsgTW2I1TAsC5IN_dH2IjCPFka5-g5fVCs/edit#)
* [와이어프레임 및 기획안](https://scene.zeplin.io/project/5ebe42c1266b3f485e753a12)
* [소개띵 Wiki](https://lab.ssafy.com/s02-final/s02p31a404/wikis/home)

### Built With
사용한 프레임워크
* [Next.js](https://nextjs.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Apollo](https://www.apollographql.com/)

<br>


<!-- GETTING STARTED -->
## :gear: Getting Started

소개띵 시작 방법입니다. 클라이언트 실행방법을 설명해드립니다.


### Installation

1. Clone the repo
```sh
git clone https://lab.ssafy.com/s02-final/s02p31a404.git
```
2. Install NPM packages
```sh
cd client
yarn install
```
3. Enter your API in `.env.build`
```JS
NEXT_APP_STAGE = 'example'
NEXT_APP_GRAPHQL_ENDPOINT = "http://www.example.com"
NEXT_APP_KAKAO_CLIENT_KEY = "example"
NEXT_APP_REST_ENDPOINT = "http://www.example.com/chat"
NEXT_APP_SOCKET_ENDPOINT = "http://www.example.com/ws-stomp"
```
4. generate and run
```
yarn generate
yarn dev
```

<br>

##  :hammer_and_pick: 개발스택

협업Tool
* [Sketch](https://www.sketch.com/)
* [Zeplin](https://zeplin.io/)
* [Postman](https://www.postman.com/)

Languege
* [TypeScript](https://www.typescriptlang.org/)
* [Java](https://java.com/ko/download/)
* [GraphQl](https://graphql.org/)

DBMS
* [MySQL](https://www.mysql.com/)
* [MongoDB](https://www.mongodb.com/)
* [Redis](https://redis.io/)

Library
* [React](https://ko.reactjs.org/)
* [Mobx](https://mobx.js.org/)
* [JWT](https://jwt.io/)
* [WebSocket](https://en.wikipedia.org/wiki/WebSocket/)

<br>


<br>
<!-- CONTACT -->

## :busts_in_silhouette:  Contact

### [Samsung Software Academy for Youth](https://www.ssafy.com/) 2th Seoul, 심화프로젝트 A404

* Frontend Developer (github and email)
  * ```류일한``` - [@RyuIl](https://github.com/RyuIL) - bbb8323@gmail.com <br>
  * ```박지홍``` - [@hongzzi](https://github.com/hongzzi) - hongzzi.dev@gmail.com <br>
* Backend Developer (github and email)
  * ```박동준``` - [@DJPark](https://github.com/DongjoonPark) - dj5427@naver.com <br>
  * ```문지현``` - [@moonjihyeon1994](https://github.com/moonjihyeon1994) - puppy613@gmail.com <br>
  * ```동명환``` - [@MueynghwanDong](https://github.com/MueynghwanDong) - v8392070@gmail.com <br>



Project Link: [https://lab.ssafy.com/s02-final/s02p31a404](https://lab.ssafy.com/s02-final/s02p31a404)

<br>

<!-- ACKNOWLEDGEMENTS -->
## 참고문서 
* [Best README Template](https://github.com/othneildrew/Best-README-Template/blob/master/README.md)
* [PWA vs React Native](https://www.kirupa.com/apps/pwa_vs_react_native.htm) 

<br>