package com.example.lab4.repository;

import com.example.lab4.entity.Point;
import com.example.lab4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    Collection<Point> findAllByUser(User user);
}
