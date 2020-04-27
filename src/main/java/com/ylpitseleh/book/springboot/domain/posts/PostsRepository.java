package com.ylpitseleh.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/*
Posts 클래스로 Database를 접근하게 해줄 JpaRepository

보통 ibatis나 MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
JPA에서는 Repository라고 부르며 인터페이스로 생성한다.
단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.

@Repository를 추가할 필요도 없다. 주의할 점은 Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다는 점이다.
둘은 아주 밀접한 관계이고, Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없다.

나중에 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야 한다면 이때 Entity 클래스와 기본 Repository는 함께 움직여야 하므로
도매인 패키지에서 함께 관리한다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // SpringDataJpa에서 제공하지 않는 메소드는 이렇게 쿼리로 작성해도 된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();


}