package com.cheffi.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import com.cheffi.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	@Query("""
		select r from Review r
		where r.restaurant.detailedAddress.province = :province 
		and r.restaurant.detailedAddress.city = :city
		and r.status = 'ACTIVE'""")
	List<Review> findByCity(@Param("province") @NonNull String province,
		@Param("city") @NonNull String city);

	@Query("select r from Review r "
		+ "left join fetch r.restaurant res "
		+ "left join fetch r.writer w "
		+ "left join fetch w.photo wp "
		+ "left join fetch r.photos rp "
		+ "where r.id = :reviewId")
	Optional<Review> findByIdWithEntities(@Param("reviewId") Long reviewId);

}
