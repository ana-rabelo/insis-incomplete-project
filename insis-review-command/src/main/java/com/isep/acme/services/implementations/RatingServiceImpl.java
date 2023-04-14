package com.isep.acme.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.model.Rating;
import com.isep.acme.repositories.RatingRepository;
import com.isep.acme.services.RatingService;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository repository;

	public Optional<Rating> findByRate(Double rate) {
		return repository.findByRate(rate);
	}

}
