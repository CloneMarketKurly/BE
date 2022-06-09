![](../../Desktop/Desktop_image/springlogo.png)
## MarketKurly clone Project - BackEnd
<div align="center">
<img width="940" alt="스크린샷 2022-04-20 오후 11 17 02" src="https://user-images.githubusercontent.com/98807506/164251304-7d922bbe-4bbd-4370-ba77-e3df065655ab.png">
 </div>



## 🤖 구현 기능
* 회원가입
* 로그인
* 메인 페이지
* 상세 페이지
* 후기 - 사진 등록
* 도움이 됐어요
* 장바구니
## 🧞‍♂️ 집중한 기능
* 로그인 시 Spring Security

## 🗓 프로젝트 기간
- 2022년 4월 15일 ~ 2022년 4월 21일
- 배포 : 2022년 4월 21일
- 유튜브 링크 : https://www.youtube.com/watch?v=-p60vltmd_g

## 👥 팀 소개
#### `Backend`
<a href="https://github.com/ksanacloud" target="_blank"><img height="40"  src="https://img.shields.io/static/v1?label=Spring&message=이현재 &color=08CE5D&style=for-the-badge&>"/></a>
<a href="https://github.com/EunheaSong" target="_blank"><img height="40"  src="https://img.shields.io/static/v1?label=Spring&message=송은혜 &color=08CE5D&style=for-the-badge&>"/></a>
<a href="https://github.com/hyun-woong" target="_blank"><img height="40"  src="https://img.shields.io/static/v1?label=Spring&message=심현웅 &color=08CE5D&style=for-the-badge&>"/></a>

#### `Frontend`
 <a href="https://github.com/JIEUN24" target="_blank"><img height="40"  src="https://img.shields.io/static/v1?label=React&message=최지은 &color=61dafb&style=for-the-badge&>"/></a>
 <a href="https://github.com/GYMMX" target="_blank"><img height="40"  src="https://img.shields.io/static/v1?label=React&message=김가경 &color=61dafb&style=for-the-badge&>"/></a>


**[(Front-end github)](https://github.com/CloneMarketKurly/FE)**

## 📜 기술스택
|분류|기술|
| :-: |:- |
|Language|<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">|
|Framework|<img src="https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white">|
|Build Tool|<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">|
|DB|<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">|
|Server|<img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=AmazonAWS&logoColor=white"> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white">|

## 🐳  ERD
![스크린샷 2022-04-20 오후 10 16 54](https://user-images.githubusercontent.com/98807506/164242191-692527fa-a6c4-4805-9dea-c906b7219b87.png)

## 🏹 Trouble Shooting
- 장바구니에서 목록 삭제시 외래키 문제로 삭제가 안됨
→ OneToMany 단방향 관계로 형성을 했는데, 외래키를 갖고있는 테이블이 없어서 외래키 참조 문제로 삭제가 되지 않음.
     ManyToOne 양방향 관계로 만들어주고, mapped를 이용해 외래키의 주인을 명시해줬다.

- 장바구니에 중복 상품을 담을 시, 수량이 추가되는 것이 아니라 새로운 목록으로 추가됨
→ 조건문을 추가해줘서 매우 잘 훌륭히 해결

- 테이블 이름을 Order 만들어서, SQL 예약어로 인한 에러
→ 테이블 이름을 Orders로 수정

## 🔨 API 설계 
<img width="984" alt="스크린샷 2022-04-20 오후 11 00 57" src="https://user-images.githubusercontent.com/98807506/164247995-de2e99fd-cf5a-46ea-80fa-5fd28344592c.png">
<img width="1040" alt="스크린샷 2022-04-20 오후 11 01 10" src="https://user-images.githubusercontent.com/98807506/164248017-4a7f6595-769f-415c-b672-b8257b829b0f.png">
