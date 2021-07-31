package com.fmovies.restapimongodb.controller;

import com.fmovies.restapimongodb.dto.VideoDto;
import com.fmovies.restapimongodb.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/videos")
    public ArrayList<VideoDto> getAllVideos() {

        videoService.getAllVideos();


        return new ArrayList<VideoDto>();
    }


    @PostMapping(value = "/videos", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public VideoDto addNewVideo(@RequestBody VideoDto videoDto) {


        videoService.createNewVideo(videoDto);

        return new VideoDto();
    }


    @GetMapping("/video/{id}")
    public VideoDto getVideo(@PathVariable("id") String id) {


        videoService.getVideo(id);

        return new VideoDto();
    }
}
