package com.example.project22.service;

import com.example.project22.model.Publisher;
import com.example.project22.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public List<Publisher> findAllByBookCount() {
        return publisherRepository.findAllOrderByBookCount();
    }

    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    public Optional<Publisher> findByName(String name) {
        return publisherRepository.findByNameIgnoreCase(name);
    }

    public List<Publisher> findByNameContaining(String nameFragment) {
        return publisherRepository.findByNameContainingIgnoreCase(nameFragment);
    }

    public List<Publisher> findByEstablishedYearGreaterThan(Integer year) {
        return publisherRepository.findByEstablishedYearGreaterThan(year);
    }

    @Transactional
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Transactional
    public Publisher update(Long id, Publisher publisherDetails) {
        return publisherRepository.findById(id)
                .map(existingPublisher -> {
                    existingPublisher.setName(publisherDetails.getName());
                    existingPublisher.setAddress(publisherDetails.getAddress());
                    existingPublisher.setWebsite(publisherDetails.getWebsite());
                    existingPublisher.setEmail(publisherDetails.getEmail());
                    existingPublisher.setPhone(publisherDetails.getPhone());
                    existingPublisher.setEstablishedYear(publisherDetails.getEstablishedYear());
                    return publisherRepository.save(existingPublisher);
                })
                .orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        publisherRepository.findById(id).ifPresent(publisherRepository::delete);
    }

    @Transactional
    public boolean exists(Long id) {
        return publisherRepository.existsById(id);
    }

    @Transactional
    public boolean existsByName(String name) {
        return publisherRepository.findByNameIgnoreCase(name).isPresent();
    }
}
