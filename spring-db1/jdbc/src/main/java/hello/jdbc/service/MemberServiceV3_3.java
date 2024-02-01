package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * 트랜잭션 - @Transactional AOP
 */
@Slf4j
//@RequiredArgsConstructor
public class MemberServiceV3_3 {

    //비즈니스 로직을 수행하는 구간에서 트랜잭션이 일어나야한다.
    //비즈니스 구간에서 오류가 발생하면 전체를 커밋하든지 롤백해야하기 때문이다.

//    private final TransactionTemplate txTemplate;
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_3(/*PlatformTransactionManager transactionManager, */MemberRepositoryV3 memberRepository) { //생성자
//        this.txTemplate = new TransactionTemplate(transactionManager);
        //txTemplate를 사용하려면 transactionManager가 필요하기 때문에 위와 같이 사용한다.
        //transactionManager 의존관계를 주입받고 내부에서 txTemplate를 사용한다.
        this.memberRepository = memberRepository;
    }

    //계좌이체하는 로직
    @Transactional //Annotation을 이용해서 transactional을 걸고 시작하겠다.
    public void accountTransfer(String fromId,String toId,int money) throws SQLException {

        //우리 메서드는 반환값이 void인 상태이므로 without을 사용한다
        //밑의 코드를 통해서 트랜잭션이 시작을 하고 비즈니스 로직을 수행하게 된다. 이후 커밋 또는 롤백이 수행됨
//        txTemplate.executeWithoutResult((status/*parameter로 넘겨준다*/) -> {

                bizLogic(fromId,toId,money);

        };

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        //ctlr + F6 을 하면 parameter의 순서를 바꿀수 있다.
        //con을 parameter로 넘겨서 다 같은 connection을 활용하게 된다.
        Member fromMember = memberRepository.findById(fromId); //보내는 회원
        Member toMember = memberRepository.findById(toId); //받는 회원

        memberRepository.update(fromId, fromMember.getMoney() - money);

        validation(toMember);

        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private static void release(Connection con) {
        if(con != null){
            try {
                //con.setAutoCommit(false);인 상태로 pool에 돌아가면 다시 conncetion을 가져오는 경우 false인 상태가 유지된다
                //그래서 true로 바꾼 다음 pool에 돌려줘야한다. connection pool을 고려해서 종료
                con.setAutoCommit(true);
                con.close();

            } catch (Exception e){
                log.info("error", e);
            }
        }
    }

    private static void validation(Member toMember) {
        //계좌 이체중 발생한 예외 로직
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
