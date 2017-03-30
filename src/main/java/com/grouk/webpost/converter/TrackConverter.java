package com.grouk.webpost.converter;

import org.springframework.stereotype.Component;

import com.grouk.webpost.model.Track;
import com.grouk.webpost.view.TrackTableDataModel;

/**
 * Created by Alena_Grouk on 3/30/2017.
 */
@Component
public class TrackConverter {
    public Track convert(TrackTableDataModel trackNumber) {
        Track track = new Track();
        track.setId(trackNumber.getId());
        track.setNumber(trackNumber.getNumber());
        track.setDescription(trackNumber.getDescription());
        return track;
    }
}
