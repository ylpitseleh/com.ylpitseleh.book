
package com.ylpitseleh.book.springboot.domain.posts;

import com.ylpitseleh.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
domain 패키지 = 도메인을 담을 페키지. 여기서 도메인이란 게시글, 댓글, 회원, 정산, 결제 등 소프트웨어에 대한 요구사항 혹은 문제 영역이라고 생각하면 됨.
               기존에 MyBatis와 같은 쿼리 매퍼를 사용했다면 dao 패키지를 떠올리겠지만, dao 패키지와는 조금 결이 다름.
               그간 xml에 쿼리를 담고, 클래스는 오로지 쿼리의 결과만 담던 일들이 모두 도메인 클래스라고 불리는 곳에서 해결된다.
 */
/*
Posts 클래스 : 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity 클래스라고 한다.
              JPA를 사용하면 DB 데이터에 작업할 경우 실제 쿼리를 날리기 보다는, 이 Entity 클래스의 수정을 통해 작업한다.

              Entity 클래스 : 절대 Setter 메소드를 만들지 않는다.
                             해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확히 구분할 수가 없어, 차후 기능 변경 시 매우 복잡해지기 때문이다.
                             Setter 대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야 한다.
 */

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor // 기본 생성자 자동 추가 public Posts() {}와 같은 효과
@Entity // 테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭함.
        // SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드를 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄.
                                                        // 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment 된다.
    private Long id;

    /*
    Column : 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨.
             기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용함. ex)VARCHAR 타입 or 사이즈 변경. 문자열 default = VARCHAR(255)
     */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /* p.93
    Builder : 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
              생성자 대신에 빌더 클래스를 사용. 빌더는 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 있음. (생성자는 X)
     */
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}