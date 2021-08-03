package com.fmovies.restapimongodb.controller;

import com.fmovies.restapimongodb.CustomResponse;
import com.fmovies.restapimongodb.model.Video;
import com.fmovies.restapimongodb.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        ArrayList<Video> movies = videoService.getVideosByType("movie");

        CustomResponse response = new CustomResponse(movies, "Here's your movies fam!");

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/tv-shows")
    public ResponseEntity getAllTVShows() {

        ArrayList<Video> tvShows = videoService.getVideosByType("tv-show");

        CustomResponse response = new CustomResponse(tvShows, "Here's your tv-shows fam!");

        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity getVideosBySearch(@RequestParam String q) {
        ArrayList<Video> results = videoService.getVideoBySearch(q);

        if (results == null || results.size() == 0)
            return new ResponseEntity(new CustomResponse(null, "No such movie or tv-show found fam!"),
                    HttpStatus.NOT_FOUND);

        return new ResponseEntity(new CustomResponse(results, "Here's the results for \"" + q + "\""), HttpStatus.OK);

    }


    @GetMapping("/featured")
    public ResponseEntity getFeaturedVideos(@RequestParam(required = false) String type) {

        ArrayList<Video> featuredVideos = videoService.getFeaturedVideos(type);

        CustomResponse response = new CustomResponse(featuredVideos, "Here's all featured videos fam!");

        return new ResponseEntity(response, HttpStatus.OK);
    }


    @PostMapping(value = "/new", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addNewVideo(@RequestBody @Valid Video video) {

        Video newVideo = videoService.createNewVideo(video);

        if (newVideo != null)
            return new ResponseEntity(new CustomResponse(newVideo, "New video added Boo!"), HttpStatus.OK);

        return new ResponseEntity(new CustomResponse("Failed", "Check your inputs again, fool!"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updateVideo(@PathVariable String id, @RequestBody @Valid Video video) {

        Video updatedVideo = videoService.updateVideo(id, video);

        if (updatedVideo != null)
            return new ResponseEntity(new CustomResponse(updatedVideo, "Video updated fam, ENJOY!"), HttpStatus.OK);

        return new ResponseEntity(new CustomResponse(null, "No such movie or tv-show exists!"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity getVideo(@PathVariable String id) {

        Optional<Video> video = videoService.getVideo(id);

        if (video == null)
            return new ResponseEntity(new CustomResponse(null, "No such movie or tv-show exists!"), HttpStatus.NOT_FOUND);

        return new ResponseEntity(new CustomResponse(video, "Here's the video fam, ENJOY!"), HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteVideo(@PathVariable String id) {
        boolean isVideoDeleted = videoService.deleteVideo(id);

        if (isVideoDeleted)
            return new ResponseEntity(new CustomResponse("Success", "Video deleted fam!"), HttpStatus.OK);

        return new ResponseEntity(new CustomResponse("Failed", "Could not find the video fam!"), HttpStatus.NOT_FOUND);
    }
}
