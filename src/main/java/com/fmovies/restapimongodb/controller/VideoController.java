package com.fmovies.restapimongodb.controller;

import com.fmovies.restapimongodb.CustomResponse;
import com.fmovies.restapimongodb.model.Video;
import com.fmovies.restapimongodb.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/")
    public ResponseEntity getAllVideos() {
        CustomResponse response = new CustomResponse(videoService.getAllVideos(), "Got all movies & tv-shows");

        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/movies")
    public ResponseEntity getAllMovies() {
        var movies = videoService.getVideosByType("movie");

        CustomResponse response = new CustomResponse(movies, "Here's your movies fam!");

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/tv-shows")
    public ResponseEntity getAllTVShows() {

        var tvShows = videoService.getVideosByType("tv-show");

        CustomResponse response = new CustomResponse(tvShows, "Here's your tv-shows fam!");

        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity getVideosBySearch(@RequestParam String q) {
        var results = videoService.getVideoBySearch(q);

        if (results.isEmpty() || results.size() == 0)
            return new ResponseEntity(new CustomResponse(null, "No such movie or tv-show found fam!"),
                    HttpStatus.NOT_FOUND);

        return  new ResponseEntity(new CustomResponse(results, "Here's the results for \"" + q +"\""), HttpStatus.OK);

    }


    @GetMapping("/featured")
    public ResponseEntity getFeaturedVideos(@RequestParam(required = false) String type) {

        var featuredVideos = videoService.getFeaturedVideos(type);

        CustomResponse response = new CustomResponse(featuredVideos, "Here's all featured videos fam!");

        return new ResponseEntity(response, HttpStatus.OK);
    }



    @PostMapping(value = "/new", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addNewVideo(@RequestBody Video video) {

        var newVideo = videoService.createNewVideo(video);

        if (newVideo != null)
            return new ResponseEntity(newVideo, HttpStatus.OK);

        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/{id}")
    public ResponseEntity getVideo(@PathVariable("id") String id) {

        var video = videoService.getVideo(id);

        var response = new CustomResponse();
        var statusMessage = HttpStatus.NOT_FOUND;

        if (video.isEmpty()) {
            response.setData(null);
            response.setMessage("No such movie or tv-show exists!");
        } else {
            response.setData(video);
            response.setMessage("Here's the video fam, ENJOY!");
            statusMessage = HttpStatus.OK;
        }


        return new ResponseEntity(response, statusMessage);
    }

}
