# fastcampus-board

패스트캠퍼스 게시판 만들기 프로젝트, 자바 + 스프링부트 공부 예제

생략된 application.yml 내용

debug: false
management:
  endpoints:
    web:
      .exposure.inclue: "*"
logging:
  level:
    com.example.fastcampusboard: debug
    org.springframework.web.servlet: debug
    org.hibernate.type.descripter.sql.BasicBinder: trace

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/fcboard
    username: 계정을 입력하세요
    password: 패스워드를 입력하세요
    driver-class-name: com.mysql.cj.jdbc.Driver
#    url: jdbc:h2:mem:testdb
#    username: sa
#    driver-class-name: org.h2.Driver
  jpa:
    defer-datasource-initialization: true
    hibernate:
      ddl-auto: create
    open-in-view: false
    show-sql: true
    properties:
      hibernate.format_sql: true
      hibernate.default_branch_fetch_size: 100
  h2:
    console:
      enabled: false
  sql:
    init:
      mode: always
  data:
    rest:
      base-path: /api
      detection-strategy: annotated

#---
#
#spring:
#  config:
#    activate:
#      on-cloud-platform: testdb
#  datasource:
#    url: jdbc:h2:mem:fcboard;mode=mysql
#    driver-class-name: org.h2.Driver
#  sql:
#    init:
#      mode: always
#  test.database.replace: none
