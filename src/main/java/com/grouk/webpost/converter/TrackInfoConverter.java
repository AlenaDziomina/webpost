package com.grouk.webpost.converter;

import com.grouk.webpost.model.TrackInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Alena on 02.04.2017.
 */
@Component
public class TrackInfoConverter {
    public TrackInfo convert(List<String> strings) {
        TrackInfo trackInfo = new TrackInfo();
        if (strings.size() > 0) {
            trackInfo.setData(strings.get(0));
        }

        if (strings.size() > 1) {
            trackInfo.setEvent(strings.get(1));
        }

        if (strings.size() > 2) {
            trackInfo.setOffice(strings.get(2));
        }
        return trackInfo;
    }
}
