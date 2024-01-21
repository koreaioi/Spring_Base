package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /*
    * ResponseBody 문자와 객체반환
    * ResponseBody를 사용하면 template를 거친 ViewResolver를 사용하지 않고
    * 데이터 그 자체를 웹 브라우저에 전달한다.
    * 즉, HTTP의 Body태그에 값을 직접 반환한다.
    * 문자면 HttpMessageConverter의 StringConverter (StringHttpMessageConverter)
    * 객체면 HttpMessageConverter의 JsonConverter (MappingJackson2HttpMessageConverter)
    * */

    //문자를 반환하는 경우, 잘 사용 안함
    @GetMapping("hello-string")
    @ResponseBody //http 에서 헤더부와 바디 부분 중 바디 부분에 return의 데이터를 직접 넣겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //"hello spring"
    }

    //객체를 반환하는 경우 json형식으로 웹 브라우저에 직접 반환
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello; //문자가 아닌 객체를 넘김
    }


    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
