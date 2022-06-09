package com.jsm.mm.domain.location.repository;

import com.jsm.mm.domain.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, String> {

    @Query(value = "select id, sido_nm, sgg_nm, emd_nm, st_aswkt(loc_coords) as loc_coords, create_datetime, modify_datetime from location where id = ?1", nativeQuery = true)
    Optional<Location> findById(String id);

    @Query(value = "select id, sido_nm, sgg_nm, emd_nm, st_aswkt(loc_coords) as loc_coords, create_datetime, modify_datetime, " +
            "(6371 * ACOS(COS(RADIANS(?2)) * COS(RADIANS(Y(st_centroid(loc_coords)))) * COS(RADIANS(X(st_centroid(loc_coords))) - RADIANS(?1)) + SIN(RADIANS(?2)) * SIN(RADIANS(Y(st_centroid(loc_coords)))))) AS distance " +
            "from location " +
            "having distance <= ?3 " +
            "order by sido_nm, sgg_nm, emd_nm", nativeQuery = true)
    List<Location> findByInDistance(double x, double y, int distance);
}