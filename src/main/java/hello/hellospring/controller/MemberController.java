package hello.hellospring.controller;


import hello.hellospring.entity.MemberDto;
import hello.hellospring.service.MemberSerivce;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MemberController {

    private final MemberSerivce memberSerivce;

    public MemberController(MemberSerivce memberSerivce) {
        this.memberSerivce = memberSerivce;
    }

    @PostMapping("/members")
    public String createMember(MemberDto memberDto){

        memberSerivce.joinMember(memberDto);

        return "success";
    }

}
