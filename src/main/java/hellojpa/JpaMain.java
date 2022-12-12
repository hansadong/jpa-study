package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member findMember = em.find(Member.class, 1L);
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(3)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }

//            Member mem = new Member();
//            mem.setId(6L);
//            mem.setName("helloJpa");
            // 트랙잭션을 커밋하는 시점에서 insert 쿼리 날림
//            System.out.println("BEFORE");
//            em.persist(mem);
//            System.out.println("AFTER");
//            System.out.println("findMember.getId() = " + findeMember.getId());
//            System.out.println("findMember.getName() = " + findeMember.getName());

            // 조회를 하면 1차 find에서 DB를 조회하고 2차 find에서는 1차 캐시에서 조회하기 때문에 select 쿼리가 하나만 날아감
//            Member findMember1= em.find(Member.class, 6L);
//            Member findMember2 = em.find(Member.class, 6L);
//
            // 영속 엔티티의 동일성 보장
//            System.out.println(findMember1 == findMember2);

            // persist 시 쓰기 지연 SQL 저장소에 쌓아두었다가 트랜잭션 커밋 시 flush 되어 insert 쿼리 날아감
//            Member member1 = new Member(7L, "A");
//            Member member2 = new Member(8L, "B");
//
//            em.persist(member1);
//            em.persist(member2);

            // 변경 감지 (자바 컬렉션과 같이 값을 수정하면 변경됨)
            // 1차 캐시에는 @Id와 Entity, 스냅샷이 있는데 커밋 시 flush()가 실행되면서
            // Entity와 스냅샷을 비교하여 변경 내용이 있을 경우 update 쿼리 날림
            Member member = em.find(Member.class, 7L);
            member.setName("AAAAA");

            // 준영속 상태가 되어 변경내용이 있어도 수정되지 않음
//            em.detach(member); // 특정 Entity만 분리

            em.clear(); // 영속성 컨텍스트에서 관리하는 Entity 모두 초기화됨

            Member member1 = em.find(Member.class, 7L);

            // 수정 시 아래 코드 작성할 필요 없음.
//            em.persist(member);
//            em.setFlushMode(FlushModeType.AUTO); // 커밋이나 쿼리를 실행할 때 플러시 (Default)
//            em.setFlushMode(FlushModeType.COMMIT); // 커밋 시에만 flush (JPQL을 사용할 경우 필요함)
//            em.flush();

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}
