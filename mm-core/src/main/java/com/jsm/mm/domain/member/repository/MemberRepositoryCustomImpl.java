package com.jsm.mm.domain.member.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.jsm.mm.domain.member.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory factory;

    @Override
    public boolean isExistMember(String column, String value) {
        BooleanBuilder builder = new BooleanBuilder();

        if ("username".equals(column)) {
            builder.and(member.username.eq(value));
        } else if ("email".equals(column)) {
            builder
                    .and(member.email.eq(value))
                    .and(member.isLeave.eq(false));
        }

        return factory
                .selectOne()
                .from(member)
                .where(builder)
                .fetchFirst() == null;
    }
}
