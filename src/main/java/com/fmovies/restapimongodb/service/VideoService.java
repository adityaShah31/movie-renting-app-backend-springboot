package com.fmovies.restapimongodb.service;


import com.fmovies.restapimongodb.model.Video;
import com.fmovies.restapimongodb.model.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public ArrayList<Video> getAllVideos() {
        return new ArrayList<Video>(videoRepository.findAll());
    }

    public ArrayList<Video> getVideosByType(String type) {
        ArrayList<Video> videos = videoRepository.findByType(type);

        return videos;
    }

    public ArrayList<Video> getFeaturedVideos(String type) {
        if (type == null || type == "")
            return videoRepository.findByFeatured(true);

        return videoRepository.findByFeaturedAndType(true, type);
    }


    public ArrayList<Video> getVideoBySearch(String searchTerm) {
        Query dbQuery = new Query();

        //Searches in title containing searchTerm (.*searchTerm.*)... i is for case-insensitive search
        dbQuery.addCriteria(Criteria.where("title").regex(".*" + searchTerm + ".*", "i"));

        return new ArrayList(mongoTemplate.find(dbQuery, Video.class));
    }


    public Video createNewVideo(Video video) {
        return videoRepository.insert(video);
    }


    public Optional<Video> getVideo(String id) {
        return videoRepository.findById(id);
    }

    public Video updateVideo(String id, Video newVideoBody) {

        Optional<Video> video = videoRepository.findById(id);

        if (video == null) {
                return null;
        }

        Video updatedVideo = video.get();
        updatedVideo.setTitle(newVideoBody.getTitle());
        updatedVideo.setSynopsis(newVideoBody.getSynopsis());
        updatedVideo.setGenres(newVideoBody.getGenres());
        updatedVideo.setReleaseYear(newVideoBody.getReleaseYear());
        updatedVideo.setRuntime(newVideoBody.getRuntime());
        updatedVideo.setBanner(newVideoBody.getBanner());
        updatedVideo.setPoster(newVideoBody.getPoster());
        updatedVideo.setType(newVideoBody.getType());
        updatedVideo.setRent(newVideoBody.getRent());
        updatedVideo.setBuy(newVideoBody.getBuy());
        updatedVideo.setActors(newVideoBody.getActors());
        updatedVideo.setDirector(newVideoBody.getDirector());
        updatedVideo.setInDemand(newVideoBody.getInDemand());
        updatedVideo.setFeatured(newVideoBody.getFeatured());
        updatedVideo.setImdbRating(newVideoBody.getImdbRating());

        return videoRepository.save(updatedVideo);
    }

    public boolean deleteVideo(String id) {
        boolean isVideoPresent = videoRepository.existsById(id);

        if (isVideoPresent)
            videoRepository.deleteById(id);

        return isVideoPresent;
    }

}
