package com.jsm.mm.domain.memberrangelocation;

import com.jsm.mm.domain.location.Location;
import com.jsm.mm.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class MemberRangeLocationId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Builder
    public MemberRangeLocationId(Member member, Location location) {
        this.member = member;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberRangeLocationId entity = (MemberRangeLocationId) o;
        return Objects.equals(this.location, entity.location) &&
                Objects.equals(this.member, entity.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, member);
    }
}