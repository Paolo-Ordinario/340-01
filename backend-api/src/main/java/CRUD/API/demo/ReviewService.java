package CRUD.API.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsBySeller(Long sellerId) {
        return reviewRepository.findBySellerId(sellerId);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review replyToReview(Long id, String reply) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            review.get().setReply(reply);
            return reviewRepository.save(review.get());
        }
        return null;
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}