package com.jsm.mm.domain.memberrangelocation;

import com.jsm.mm.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "member_range_location")
public class MemberRangeLocation extends BaseTimeEntity {

    @EmbeddedId
    private MemberRangeLocationId id;

    @Builder
    public MemberRangeLocation(MemberRangeLocationId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberRangeLocation that = (MemberRangeLocation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}