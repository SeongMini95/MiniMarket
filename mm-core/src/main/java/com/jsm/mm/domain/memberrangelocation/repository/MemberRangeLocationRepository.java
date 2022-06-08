package com.jsm.mm.domain.memberrangelocation.repository;

import com.jsm.mm.domain.memberrangelocation.MemberRangeLocation;
import com.jsm.mm.domain.memberrangelocation.MemberRangeLocationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRangeLocationRepository extends JpaRepository<MemberRangeLocation, MemberRangeLocationId> {
}