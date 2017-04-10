package com.grouk.webpost.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.grouk.webpost.model.Track;

/**
 * Track DAO
 * Created by Alena_Grouk on 3/29/2017.
 */
@Component
public class TrackDao {

    private final static String SQL_GET_LIST = "Select * from TRACK";
    private final static String SQL_CREATE = "Insert into TRACK (number, description) VALUES (?, ?)";
    private final static String SQL_UPDATE = "Update TRACK set NUMBER=?, DESCRIPTION=? where id=?";
    private final static String SQL_DELETE = "Delete from TRACK where id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Track> getList() {
        return jdbcTemplate.query(SQL_GET_LIST, (rs, rowNum) -> {
            Track track = new Track();
            track.setId(rs.getInt("id"));
            track.setNumber(rs.getString("number"));
            track.setDescription(rs.getString("description"));
            track.setImage(rs.getBytes("image"));
            return track;
        });
    }

    public void create(Track track) {
        jdbcTemplate.update(SQL_CREATE, track.getNumber(), track.getDescription());
    }

    public void update(Track track) {
        jdbcTemplate.update(SQL_UPDATE, track.getNumber(), track.getDescription(), track.getId());
    }

    public void delete(int trackId) {
        jdbcTemplate.update(SQL_DELETE, trackId);
    }

}
