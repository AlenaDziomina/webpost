package com.grouk.webpost.manager;

import java.util.List;
import java.util.stream.Collectors;

import com.grouk.webpost.model.TrackInfo;
import com.grouk.webpost.view.TrackInfoDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grouk.webpost.converter.TrackConverter;
import com.grouk.webpost.model.Track;
import com.grouk.webpost.service.TrackInfoService;
import com.grouk.webpost.service.TrackService;
import com.grouk.webpost.view.TrackTableDataModel;

/**
 * Created by Alena_Grouk on 3/29/2017.
 */
@Component
public class TrackManager {

    @Autowired
    private TrackService trackService;

    @Autowired
    private TrackInfoService trackInfoService;

    @Autowired
    private TrackConverter converter;

    public List<TrackTableDataModel> getTrackNumbers() {
        List<Track> trackList = trackService.getTrackList();
        return trackList.stream().map(TrackTableDataModel::new).collect(Collectors.toList());
    }

    public void saveTrackNumber(TrackTableDataModel trackNumber) {
        Track track = converter.convert(trackNumber);
        trackService.saveTrack(track);
    }

    public void deleteTrackNumber(TrackTableDataModel trackNumber) {
        trackService.deleteTrack(trackNumber.getId());
    }

    public List<TrackInfoDataModel> getTrackInfo(TrackTableDataModel trackNumber) {
        List<TrackInfo> trackInfoList = trackInfoService.getTrackInfo(trackNumber.getNumber());
        return trackInfoList.stream().map(TrackInfoDataModel::new).collect(Collectors.toList());
    }

}
