package com.grouk.webpost.model;

/**
 * Track status
 * Created by Alena on 05.04.2017.
 */
public enum TrackStatus {
    UNKNOWN(0), SENDED(1), MINSK(2), BORISOV(3), ZHODINO(4);

    private int status;

    TrackStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
