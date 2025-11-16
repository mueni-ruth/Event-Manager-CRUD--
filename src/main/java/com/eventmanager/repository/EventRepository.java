package com.eventmanager.repository;
 
import com.eventmanager.entity.Event;

import com.eventmanager.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
 
import java.time.LocalDate;

import java.util.List;
 
@Repository

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByOrganizer(User organizer);

    @Query("SELECT e FROM Event e WHERE e.expiryDate >= :currentDate ORDER BY e.uploadDate DESC")

    List<Event> findActiveEvents(@Param("currentDate") LocalDate currentDate);

    List<Event> findByOrganizerAndExpiryDateGreaterThanEqual(User organizer, LocalDate currentDate);

    // Add this method to debug

    @Query("SELECT COUNT(e) FROM Event e")

    long countEvents();

}
 