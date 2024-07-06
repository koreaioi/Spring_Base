package hello.hellospring.service;

import hello.hellospring.entity.Member;
import hello.hellospring.entity.MemberDto;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberSerivce {

    private final MemberRepository memberRepository;

    public MemberSerivce(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void joinMember(MemberDto memberDto) {

        Member member = new Member();
        member.setText(memberDto.getText());

        memberRepository.save(member);
    }

}
