package com.jsm.mm.domain.memberlocation;

import com.jsm.mm.domain.location.Location;
import com.jsm.mm.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class MemberLocationId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "location_seq", nullable = false)
    private int locationSeq;

    @Builder
    public MemberLocationId(Member member, Location location, int locationSeq) {
        this.member = member;
        this.location = location;
        this.locationSeq = locationSeq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberLocationId entity = (MemberLocationId) o;
        return Objects.equals(this.locationSeq, entity.locationSeq) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.member, entity.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationSeq, location, member);
    }
}