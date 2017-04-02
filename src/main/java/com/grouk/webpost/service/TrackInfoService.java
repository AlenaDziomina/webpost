package com.grouk.webpost.service;

import com.grouk.webpost.converter.TrackInfoConverter;
import com.grouk.webpost.model.TrackInfo;
import com.grouk.webpost.util.ResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alena_Grouk on 3/30/2017.
 */
@Component
public class TrackInfoService {

    @Autowired
    private TrackInfoConverter converter;

    @Value("${webservices.belpost.by}")
    private String url;

    public List<TrackInfo> getTrackInfo(String trackNumber) {
        Map<String, String> params = new HashMap<>();
        params.put("num", trackNumber);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class, params);
        List<List<String>> info = ResponseParser.parseResponse(result);
        return info.stream().map(converter::convert).collect(Collectors.toList());
    }
}