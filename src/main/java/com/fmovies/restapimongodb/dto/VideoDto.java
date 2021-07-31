package com.fmovies.restapimongodb.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("videos")
public class VideoDto {
   @Id
   String id;

   String title;
   String synopsis;
   ArrayList<String> genres;
   String releaseYear;
   String runtime;
   String poster;
   String banner;
   String type;
   Float rent;
   Float buy;
   Float IMDbRating;
   ArrayList<String> actors;
   String director;
   Boolean inDemand;
   Boolean featured;


   public VideoDto(){};
}
