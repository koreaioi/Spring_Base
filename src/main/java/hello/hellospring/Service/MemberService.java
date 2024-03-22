package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    //DI 사용 X
    private final MemberRepository memberRepository = new MemoryMemberRepository();


    /*
    * 회원 가입
    * 같은 이름이 있으면 저장하지 않는 유효성 검사를 한다.
    * */
    public Long join(Member member){ //join - 비지니스 용어
        //유효성 검사
/*      Optional로 따로 저장하는게 좋지는 않으니 메서드 코드 처럼 반환값에 바로 . (dot)을 사용해 유효성 검증
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
*/
        validateDuplicate(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicate(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /*
    * Id로 멤버 조회
    * */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
