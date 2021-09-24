package com.safecornerscoffee.borders.repository;

import com.safecornerscoffee.borders.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public Member save(Member member) {
        if (member.getId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
        }

        return member;
    }

    public Optional<Member> findById(Long id) {

        Member member = em.find(Member.class, id);
        return Optional.of(member);
    }

    public List<Member> findAll() {
       return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Optional<Member> findByUsername(String username) {
        Member member =  em.createQuery("select m from Member m where m.username = :username", Member.class)
                        .setParameter("username", username)
                        .getSingleResult();
        return Optional.of(member);
    }

    public void delete(Member member) {
        em.remove(member);
    }
}
