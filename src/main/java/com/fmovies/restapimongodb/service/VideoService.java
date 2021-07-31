package com.fmovies.restapimongodb.service;


import com.fmovies.restapimongodb.dto.VideoDto;
import com.fmovies.restapimongodb.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private Video videoRepository;


    public ArrayList<VideoDto> getAllVideos() {


        return new ArrayList<VideoDto>(videoRepository.findAll());
    }



    public VideoDto createNewVideo(VideoDto videoDto) {


        return videoRepository.insert(videoDto);
    }


    public Optional<VideoDto> getVideo(String id) {

        return videoRepository.findById(id);

    }
}
