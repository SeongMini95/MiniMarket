package com.jsm.mm.domain.certify;

import com.jsm.mm.domain.BaseTimeEntity;
import com.jsm.mm.domain.certify.converter.CertifyCdConverter;
import com.jsm.mm.domain.certify.enums.CertifyCd;
import com.jsm.mm.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "certify")
public class Certify extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "certify_key", nullable = false)
    private String certifyKey;

    @Convert(converter = CertifyCdConverter.class)
    @Column(name = "certify_cd", nullable = false)
    private CertifyCd certifyCd;

    @Column(name = "is_used", nullable = false)
    private boolean isUsed;

    @Column(name = "expire_datetime", nullable = false)
    private LocalDateTime expireDatetime;

    @Builder
    public Certify(Member member, String certifyKey, CertifyCd certifyCd, boolean isUsed, LocalDateTime expireDatetime) {
        this.member = member;
        this.certifyKey = certifyKey;
        this.certifyCd = certifyCd;
        this.isUsed = isUsed;
        this.expireDatetime = expireDatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Certify certify = (Certify) o;
        return id != null && Objects.equals(id, certify.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}