package com.jsm.mm.domain.memberrangelocation.repository;

import com.jsm.mm.domain.member.Member;
import com.jsm.mm.domain.memberrangelocation.MemberRangeLocation;
import com.jsm.mm.domain.memberrangelocation.MemberRangeLocationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemberRangeLocationRepository extends JpaRepository<MemberRangeLocation, MemberRangeLocationId>, MemberRangeLocationRepositoryCustom {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from MemberRangeLocation mrl where mrl.id.member = ?1")
    void deleteAllByMember(Member member);
}