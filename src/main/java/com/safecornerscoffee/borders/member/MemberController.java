package com.safecornerscoffee.borders.member;

import com.safecornerscoffee.borders.member.exception.MemberNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MemberController {

    private final MemberService memberService;
    private final MemberModelAssembler assembler;

    public MemberController(MemberService memberService, MemberModelAssembler assembler) {
        this.memberService = memberService;
        this.assembler = assembler;
    }

    @GetMapping("/members")
    public CollectionModel<EntityModel<Member>> all() {
        List<Member> all = memberService.findAll();
        List<EntityModel<Member>> collect = all.stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(collect,
                linkTo(methodOn(MemberController.class).all()).withSelfRel());
    }

    @GetMapping("/members/{id}")
    public EntityModel<Member> one(@PathVariable Long id) {
        Member member = memberService.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        return assembler.toModel(member);
    }

    @PutMapping("/members/{id}")
    public EntityModel<Member> replaceMember(@RequestBody Member member, Long id) {
        Member savedMember = memberService.findById(id).map(m -> {
            m.setUsername(member.getUsername());
            m.setPassword(member.getPassword());
            m.setAddress(member.getAddress());
            return memberService.save(m);
        }).orElseGet(() -> {
            member.setId(id);
            return memberService.save(member);    
        });
        
        return assembler.toModel(savedMember);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        memberService.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
