package com.example.moviereview.service;

import com.example.moviereview.model.Review;
import com.example.moviereview.model.Movie;
import com.example.moviereview.repository.ReviewRepository;
import com.example.moviereview.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    public Review addReview(Long movieId, Review review) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isPresent()) {
            review.setMovie(movieOptional.get());
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("Movie not found");
        }
    }

    public Review updateReview(Long reviewId, Review reviewDetails) {
        return reviewRepository.findById(reviewId).map(review -> {
            review.setComment(reviewDetails.getComment());
            review.setRating(reviewDetails.getRating());
            return reviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found"));
    }
}
