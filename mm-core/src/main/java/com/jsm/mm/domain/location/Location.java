package com.jsm.mm.domain.location;

import com.jsm.mm.domain.BaseTimeEntity;
import com.jsm.mm.domain.location.converter.centCoordsConverter;
import com.jsm.mm.domain.location.converter.locCoordsConverter;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "location")
public class Location extends BaseTimeEntity {

    @Id
    @Column(name = "id", nullable = false, length = 8)
    private String id;

    @Column(name = "sido_nm", nullable = false, length = 15)
    private String sidoNm;

    @Column(name = "sgg_nm", nullable = false, length = 15)
    private String sggNm;

    @Column(name = "emd_nm", nullable = false, length = 15)
    private String emdNm;

    @Convert(converter = locCoordsConverter.class)
    @Column(name = "loc_coords", nullable = false)
    private MultiPolygon locCoords;

    @Convert(converter = centCoordsConverter.class)
    @Column(name = "cent_coords", nullable = false)
    private Point centCoords;

    @Builder
    public Location(String id, String sidoNm, String sggNm, String emdNm, MultiPolygon locCoords, Point centCoords) {
        this.id = id;
        this.sidoNm = sidoNm;
        this.sggNm = sggNm;
        this.emdNm = emdNm;
        this.locCoords = locCoords;
        this.centCoords = centCoords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Location location = (Location) o;
        return id != null && Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}