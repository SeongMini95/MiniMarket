package com.jsm.mm.domain.memberrangelocation.repository;

import com.jsm.mm.domain.memberrangelocation.MemberRangeLocation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class MemberRangeLocationRepositoryCustomImpl implements MemberRangeLocationRepositoryCustom {

    private final JPAQueryFactory factory;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveAllBulk(List<MemberRangeLocation> memberRangeLocationList) {
        String sql = "insert into member_range_location values (?, ?, ?, ?)";
        Timestamp now = new Timestamp(new Date().getTime());

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, memberRangeLocationList.get(i).getId().getMember().getId());
                ps.setString(2, memberRangeLocationList.get(i).getId().getLocation().getId());
                ps.setTimestamp(3, now);
                ps.setTimestamp(4, now);
            }

            @Override
            public int getBatchSize() {
                return memberRangeLocationList.size();
            }
        });
    }
}
