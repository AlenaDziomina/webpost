package com.grouk.webpost.util;

import com.grouk.webpost.model.TrackStatus;
import com.grouk.webpost.view.TrackInfoDataModel;

import java.util.List;

/**
 * Track status define util
 * Created by Alena on 05.04.2017.
 */
public class StatusUtil {
    private static final String MINSK = "MINSK";
    private static final String BORISOV = "Борисов";
    private static final String ZHODINO = "Жодино";

    public static TrackStatus defineStatus(List<TrackInfoDataModel> trackInfo) {
        if (trackInfo.isEmpty()) {
            return TrackStatus.UNKNOWN;
        }

        String lastOffice = trackInfo.get(trackInfo.size() - 1).getOffice();
        if (lastOffice.contains(MINSK)) {
            return TrackStatus.MINSK;
        } else if (lastOffice.contains(BORISOV)) {
            return TrackStatus.BORISOV;
        } else if (lastOffice.contains(ZHODINO)) {
            return TrackStatus.ZHODINO;
        }
        return TrackStatus.SENDED;
    }
}
