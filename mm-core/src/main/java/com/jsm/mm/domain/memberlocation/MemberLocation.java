package com.jsm.mm.domain.memberlocation;

import com.jsm.mm.domain.BaseTimeEntity;
import com.jsm.mm.domain.memberlocation.converter.LocationRangeConverter;
import com.jsm.mm.domain.memberlocation.enums.LocationRange;
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
@Table(name = "member_location")
public class MemberLocation extends BaseTimeEntity {

    @EmbeddedId
    private MemberLocationId id;

    @Convert(converter = LocationRangeConverter.class)
    @Column(name = "location_range", nullable = false)
    private LocationRange locationRange;

    @Column(name = "is_certify", nullable = false)
    private boolean isCertify;

    @Builder
    public MemberLocation(MemberLocationId id, LocationRange locationRange, boolean isCertify) {
        this.id = id;
        this.locationRange = locationRange;
        this.isCertify = isCertify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberLocation that = (MemberLocation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}