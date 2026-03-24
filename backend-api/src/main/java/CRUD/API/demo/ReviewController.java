package CRUD.API.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/seller/{sellerId}")
    public List<Review> getReviewsBySeller(@PathVariable Long sellerId) {
        return reviewService.getReviewsBySeller(sellerId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        if (review.isPresent()) {
            return ResponseEntity.ok(review.get());
        } else {
            return ResponseEntity.status(404).body("Review with ID " + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<Object> createReview(@RequestBody Review review) {
        Review saved = reviewService.createReview(review);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}/reply")
    public ResponseEntity<Object> replyToReview(@PathVariable Long id, @RequestBody String reply) {
        Review updated = reviewService.replyToReview(id, reply);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(404).body("Review not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long id) {
        Optional<Review> existing = reviewService.getReviewById(id);
        if (existing.isPresent()) {
            reviewService.deleteReview(id);
            return ResponseEntity.ok("Review deleted.");
        } else {
            return ResponseEntity.status(404).body("Review not found.");
        }
    }
}