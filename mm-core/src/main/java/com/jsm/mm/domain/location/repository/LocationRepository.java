package com.jsm.mm.domain.location.repository;

import com.jsm.mm.domain.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, String> {

    @Query(value = "select id, sido_nm, sgg_nm, emd_nm, st_aswkt(loc_coords) as loc_coords, st_aswkt(cent_coords) as cent_coords, create_datetime, modify_datetime from location where id = ?1", nativeQuery = true)
    Optional<Location> findById(String id);

    @Query(value = "select id, sido_nm, sgg_nm, emd_nm, st_aswkt(loc_coords) as loc_coords, st_aswkt(cent_coords) as cent_coords, create_datetime, modify_datetime " +
            "from location " +
            "where st_distance_sphere(st_geomfromtext(?1), st_centroid(loc_coords)) <= ?2 " +
            "order by sido_nm, sgg_nm, emd_nm", nativeQuery = true)
    List<Location> findByInDistance(String point, int meter);
}