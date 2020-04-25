package com.ylpitseleh.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/*
Posts 클래스로 Database를 접근하게 해줄 JpaRepository
보통 ibatis나 MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
JPA에서는 Repository라고 부르며 인터페이스로 생성한다.
단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();


}