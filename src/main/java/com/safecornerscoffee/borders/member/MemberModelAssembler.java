package com.safecornerscoffee.borders.member;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MemberModelAssembler implements RepresentationModelAssembler<Member, EntityModel<Member>> {
    @Override
    public EntityModel<Member> toModel(Member member) {
        return EntityModel.of(member,
                linkTo(methodOn(MemberController.class).one(member.getId())).withSelfRel(),
                linkTo(methodOn(MemberController.class).all()).withRel("members"));
    }
}
