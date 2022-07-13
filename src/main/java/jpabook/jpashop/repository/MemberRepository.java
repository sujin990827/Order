package jpabook.jpashop.repository;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {

        em.persist(member);  //jpa 가 저장하는 로직
    }
    //조회
    public Member findOne(Long id){

        return em.find(Member.class, id);
    }

    //회원목록때문에 리스트 필요하다
    public List<Member> findAll(){
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }

    //이름으로 회원 검색
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
