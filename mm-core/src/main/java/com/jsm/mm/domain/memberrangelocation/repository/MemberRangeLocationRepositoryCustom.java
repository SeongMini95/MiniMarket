package com.jsm.mm.domain.memberrangelocation.repository;

import com.jsm.mm.domain.memberrangelocation.MemberRangeLocation;

import java.util.List;

public interface MemberRangeLocationRepositoryCustom {

    void saveAllBulk(List<MemberRangeLocation> memberRangeLocationList);
}
