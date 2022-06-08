package com.jsm.mm.domain.location.repository;

import com.jsm.mm.domain.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}