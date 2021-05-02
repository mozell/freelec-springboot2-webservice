package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * 1. 컨트롤러를 JSON을 반환하는 커트롤러로 만들어준다.
 * 2. 예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다.
 */
public class HelloController {

    @GetMapping("/hello")
    /**
     * 1. HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어준다
     * 2. 예전에는 @RequestMapping(method=RequsetMethod.GET) 으로 사용되었다.
     *    이제 이 프로젝트는 /hello 요청이 오면 문자열 "hello"를 반환하는 기능을 가지게 되었다.
     */
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
                                    /** @RequsetParam
                                     *  1. 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
                                     *  2. 여기서는 외부에서 name(@RequsetParam("name")) 이란 이름으로 넘긴 파라미터를
                                     *     메소드 파라미터 name(String name) 에 저장하게 된다.
                                     */
        return new HelloResponseDto(name, amount);
    }

}
