package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long>{
}

/**
 * 1. JpaRepository 를 상속하면 기본 CRUD 메소드가 자동 생성됨
 * 2. @Repository 를 추가할 필요 없음
 * 3. Entity 클래스와 기본 Entity Repository 는 함께 위치해야한다 --> Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없다.
 */