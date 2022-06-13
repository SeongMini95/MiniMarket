package com.jsm.mm.domain.member;

import com.jsm.mm.domain.BaseTimeEntity;
import com.jsm.mm.domain.member.enums.RoleName;
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
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 15)
    private String username;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "nickname", nullable = false, length = 15)
    private String nickname;

    @Column(name = "profile")
    private String profile;

    @Column(name = "is_leave", nullable = false)
    private boolean isLeave;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, length = 20)
    private RoleName roleName;

    @Builder
    public Member(String username, String email, String password, String nickname, String profile, boolean isLeave, RoleName roleName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profile = profile;
        this.isLeave = isLeave;
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Member member = (Member) o;
        return id != null && Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}