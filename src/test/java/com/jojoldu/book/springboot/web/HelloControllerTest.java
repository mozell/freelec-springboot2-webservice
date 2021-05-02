package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
/** @RunWith
 * 1. 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행 시킵니다.
 * 2. 여기서는 SpringRunner 라는 스프링 실행자를 실행시킵니다.
 * 3. 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 합니다.
 */
@WebMvcTest(controllers = HelloController.class)
/** @WebMvcTest
 * 1. 여러 스프링 테스트 어노테이션 중, Web(Spring Mvc)에 집중할 수 있는 어노테이션이다.
 * 2. 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
 * 3. 단, @Service, @Component, @Repository 등은 사용할 수 없다.
 * 4. 여기서는 컨트롤러만 사용하기 때문에 선언했다.
 */
public class HelloControllerTest {

    @Autowired
    /** @Autowired
     * 1. 스프링이 관리하는 빈(Bean)을 주입받는다.
     */
    private MockMvc mvc;
    /** private MockMvc mvc
     *  1. 웹 API를 테스트할 때 사용한다.
     *  2. 스프링 MVC테스트의 시작점
     *  3. 이 클래스를 통해 HTTP GET, POST 등에 대한 API를 테스트 할 수 있다.
     */

    @Test
    public void returnHello() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))          /** MockMvc를 통해 /hello 주소로 HTTP GET을 요청한다.*/
                .andExpect(status().isOk())           /** mvc.perform의 결과를 검증한다, HTTP Reader의 Status를 검증한다. */
                .andExpect(content().string(hello));  /** mvc.perform의 결과를 검증한다, 응답 본문의 내용을 검증한다. */
    }

    @Test
    public void returnHelloDto() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)                      /** .param : API 테스트할 때 사용될 요청 파라미터를 설정한다, 값은 String만 허용된다 */
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))   /** jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명을 명시한다 */
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}