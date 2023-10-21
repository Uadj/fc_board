# 게시판 예제

### 방법 1.  CMD창에서 docker hub public repository image 실행

#### 1. Image 다운로드

```
docker pull heddke7/board-database
```
```
docker pull heddke7/board-redis
```
```
docker pull heddke7/board-app
```

#### 2. 이미지 실행
```
docker run -d -p 3306:3306 --name board-database -e MARIADB_DATABASE=board -e MARIADB_ROOT_PASSWORD=1234 heddke7/board-database
```
```
docker run -d -p 6379:6379 --name board-database heddke7/board-redis
```
```
docker run -d -p 80:8080--name board-app -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=1234 -e SPRING_PROFILES_ACTIVE=prod -e KAKAO_OAUTH_CLIENT_ID=본인Key -e KAKAO_OAUTH_CLIENT_SECRET=본인Key heddke7/board-app
```

#### 3. http://localhost 로 접속
---
### 방법 2. Git Clone IDE Local 환경

1. 프로젝트 경로에 .env파일 생성

```
SPRING_DATASOURCE_USERNAME= root
SPRING_DATASOURCE_PASSWORD= 1234
SPRING_PROFILES_ACTIVE= prod
KAKAO_OAUTH_CLIENT_ID =
KAKAO_OAUTH_CLIENT_SECRET =
```

2. JAR 파일 생성

```./gradlew bootJar```

3. 컨테이너 실행

``` docker-compose -up ```

4. http://localhost 로 접속

---

테스트용 계정 <br>
Id : uno <br>
Password : asdf1234

docker-compose-
Spring Boot

* Spring Boot Actuator
* Spring Web
* Spring Data JPA
* Rest Repositories
* Rest Repositories HAL Explorer
* Spring Security
* Lombok
* Spring Boot DevTools
* Spring Configuration Processor
* QueryDSL 5.0.0


DB

* MariaDB


View

* Thymeleaf
* Bootstrap 5.2.0-Beta1

API Endpoint url

https://docs.google.com/spreadsheets/d/1k0O3cv1Ij_UOYIXUZwSlAGVF-jG7iiH779N8UxxA61A/edit#gid=0
