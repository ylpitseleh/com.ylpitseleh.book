package com.ylpitseleh.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Application 클래스 - 앞으로 만들 프로젝트의 메인 클래스
/*
@springBootApplication - 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
                         @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야함.
*/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /*
        SpringApplication.run으로 인해 내장 WAS를 실행.
        내장 WAS : 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행.
        = 서버에 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar 파일(실행 가능한 Java 패키징 파일)로 실행하면 됨.
        '언제 어디서나 같은 환경에서 스프링 부트를 배포'할 수 있기 때문에 내장 WAS 사용을 권장.
        외장 WAS를 쓰면 모든 서버는 WAS의 종류와 버전, 설정을 일치시켜야 하는 번거로움. (ex. 서버가 30대일 때)
         */

        SpringApplication.run(Application.class, args);
    }
}
