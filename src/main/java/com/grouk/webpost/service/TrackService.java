package com.grouk.webpost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grouk.webpost.dao.TrackDao;
import com.grouk.webpost.model.Track;

/**
 * Created by Alena_Grouk on 3/29/2017.
 */
@Component
public class TrackService {

    @Autowired
    private TrackDao trackDao;

    public List<Track> getTrackList() {
        return trackDao.getList();
    }

    public void saveTrack(Track track) {
        if (track.getId() == null) {
            trackDao.create(track);
        } else {
            trackDao.update(track);
        }
    }

    public void deleteTrack(Integer id) {
        trackDao.delete(id);
    }
}
