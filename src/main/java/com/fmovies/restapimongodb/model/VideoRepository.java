package com.fmovies.restapimongodb.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {

    ArrayList<Video> findByType(String type);
    ArrayList<Video> findByFeatured(boolean featured);
    ArrayList<Video> findByFeaturedAndType(boolean featured, String type);

//    ArrayList<Video> findByTitleContaining
}
