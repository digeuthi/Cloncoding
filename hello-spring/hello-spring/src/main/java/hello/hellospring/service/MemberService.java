package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService { //테스트 만들기 ctrl + shift + T
    private final MemberRepository memberRepository;
    //레포지토리에서 가져와서 구현을 해야하니까 새로 만들어줌


    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    } // private하게 설정된걸 외부에서 주입을 할수 있도록 바꿔줌


    //회원가입
    public Long join(Member member){
        //같은 이름의 회원을 가입하면 안된다는 제한인 경우
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //ctrl + alt + v  result 에 괸련된걸 생성해줌
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 이름입니다.");
//        }); //이미 m이 존재하는 상황이면 예외를 던지는 람다표현식 사용
        
        //메서드 걸리는 시간 측정
//        long start = System.currentTimeMillis();
//
//        try {
//            validateDuplicateMember(member);
//            memberRepository.save(member);
//            return member.getId();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start; //총 걸린 시간 측정
//            System.out.println("join = " + timeMs + "ms");
//        }

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                             throw new IllegalStateException("이미 존재하는 이름입니다.");
        }); //이렇게 정리해서 사용할수도 있다. //ctrl + alt + m 해서 메서드 정의가능
    }

    //전체 회원 조회
    public List<Member> findMembers(){
//        long strat = System.currentTimeMillis();
//        try {
//            return  memberRepository.findAll();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - strat;
//            System.out.println("findMembers = " + timeMs + "ms");
//        }

        return  memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
