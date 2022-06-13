package com.jsm.mm.domain.memberlocation.repository;

import com.jsm.mm.domain.memberlocation.MemberLocation;
import com.jsm.mm.domain.memberlocation.MemberLocationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLocationRepository extends JpaRepository<MemberLocation, MemberLocationId> {
}