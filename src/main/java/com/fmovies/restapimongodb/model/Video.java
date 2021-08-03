package com.fmovies.restapimongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document("videos")
public class Video {

   //POJO //DTO //Bean //Entity
   @Id
   private String id;

   private String title;
   private String synopsis;
   private ArrayList<String> genres;
   private String releaseYear;
   private String runtime;
   private String poster;
   private String banner;
   private String type;
   private String rent;
   private String buy;
   private String imdbRating;
   private ArrayList<String> actors;
   private String director;
   private Boolean inDemand = false;
   private Boolean featured = false;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getSynopsis() {
      return synopsis;
   }

   public void setSynopsis(String synopsis) {
      this.synopsis = synopsis;
   }

   public ArrayList<String> getGenres() {
      return genres;
   }

   public void setGenres(ArrayList<String> genres) {
      this.genres = genres;
   }

   public String getReleaseYear() {
      return releaseYear;
   }

   public void setReleaseYear(String releaseYear) {
      this.releaseYear = releaseYear;
   }

   public String getRuntime() {
      return runtime;
   }

   public void setRuntime(String runtime) {
      this.runtime = runtime;
   }

   public String getPoster() {
      return poster;
   }

   public void setPoster(String poster) {
      this.poster = poster;
   }

   public String getBanner() {
      return banner;
   }

   public void setBanner(String banner) {
      this.banner = banner;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getRent() {
      return rent;
   }

   public void setRent(String rent) {
      this.rent = rent;
   }

   public String getBuy() {
      return buy;
   }

   public void setBuy(String buy) {
      this.buy = buy;
   }

   public String getImdbRating() {
      return imdbRating;
   }

   public void setImdbRating(String imdbRating) {
      this.imdbRating = imdbRating;
   }

   public ArrayList<String> getActors() {
      return actors;
   }

   public void setActors(ArrayList<String> actors) {
      this.actors = actors;
   }

   public String getDirector() {
      return director;
   }

   public void setDirector(String director) {
      this.director = director;
   }

   public Boolean getInDemand() {
      return inDemand;
   }

   public void setInDemand(Boolean inDemand) {
      this.inDemand = inDemand;
   }

   public Boolean getFeatured() {
      return featured;
   }

   public void setFeatured(Boolean featured) {
      this.featured = featured;
   }

   public Video(){};

   @Override
   public String toString() {
      return "Video{" +
              "id='" + id + '\'' +
              ", title='" + title + '\'' +
              ", synopsis='" + synopsis + '\'' +
              ", genres=" + genres +
              ", releaseYear='" + releaseYear + '\'' +
              ", runtime='" + runtime + '\'' +
              ", poster='" + poster + '\'' +
              ", banner='" + banner + '\'' +
              ", type='" + type + '\'' +
              ", rent=" + rent +
              ", buy=" + buy +
              ", IMDbRating=" + imdbRating +
              ", actors=" + actors +
              ", director='" + director + '\'' +
              ", inDemand=" + inDemand +
              ", featured=" + featured +
              '}';
   }
}
