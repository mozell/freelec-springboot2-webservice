package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter /** 클래스 내 모든 필드의 Getter 메소드 자동 생성 */
@NoArgsConstructor /** 기본 생성자 자동 추가 [public Posts() {} 와 같은 효과]*/
@Entity  /** 테이블과 링크될 클래스를 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭한다.*/
public class Posts {

    @Id /** 해당 테이블의 PK */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /** PK의 생성규칙, GenerationType.IDENTITY 옵션을 사용해야만 auto_increment 됌 */
    private Long id;

    @Column(length = 500, nullable = false) /** 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다, 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있는 경우 */
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder /** 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함 */
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}

/**
 *  Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
 *
 *  Setter 가 없는 이 상황에서 어떻게 값을 채워 DB에 삽입하는가?
 *  --> 생성자를 통해 최종값을 채운 후 DB에 삽입
 *  --> 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경
 *
 *  이 강의에서는 생성자 대신, @Builder 클래스를 사용한다.
 */