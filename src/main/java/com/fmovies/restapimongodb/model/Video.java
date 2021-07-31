package com.fmovies.restapimongodb.model;

import com.fmovies.restapimongodb.dto.VideoDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Video extends MongoRepository<VideoDto, String> {
}
