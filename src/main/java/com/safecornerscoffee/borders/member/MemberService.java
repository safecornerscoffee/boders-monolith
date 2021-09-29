package com.safecornerscoffee.borders.member;

import com.safecornerscoffee.borders.member.exception.MemberDuplicatedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Member save(Member member) {
        isDuplicateMember(member);
        return repository.save(member);
    }

    private void isDuplicateMember(Member member) {
        Optional<Member> findMember = repository.findByUsername(member.getUsername());
        if (findMember.isPresent()) {
            throw new MemberDuplicatedException(member.getUsername());
        }
    }

    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    public List<Member> findAll() {
        return repository.findAll();
    }

    public Optional<Member> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
