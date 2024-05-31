package com.example.lab4.controller;

import com.example.lab4.dto.PointDTO;
import com.example.lab4.dto.RowDTO;
import com.example.lab4.entity.Point;
import com.example.lab4.repository.PointRepository;
import com.example.lab4.service.PointCheckService;
import com.example.lab4.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/points")
public class PointController {
    private final long PAGE_SIZE = 10L;
    private UserService userService;
    private PointCheckService pointCheckService;
    private PointRepository pointRepository;



    @PostMapping("/add")
    public ResponseEntity<?> addPoint(@Validated @RequestBody PointDTO pointDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();

        com.example.lab4.entity.User realUser = userService.findUserByUsername(username);

        RowDTO rowDTO = pointCheckService.processPoint(pointDTO);
        pointRepository.save(new Point(rowDTO, realUser));

        return ResponseEntity.ok(rowDTO);
    }

    @GetMapping(value = "/list", produces = "application/json")
    ResponseEntity<?> getUserPoints(int page) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        com.example.lab4.entity.User user = userService.findUserByUsername(username);
        return ResponseEntity.ok(
                pointRepository.findAllByUser(user).stream()
                        .map(RowDTO::new).sorted(Comparator.comparing(RowDTO::getTimeStamp).reversed())
                        .skip(page * PAGE_SIZE).limit(PAGE_SIZE)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/count", produces = "application/json")
    ResponseEntity<?> getPointsCount() {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        com.example.lab4.entity.User user = userService.findUserByUsername(username);
        return ResponseEntity.ok(
                pointRepository.findAllByUser(user).size()
        );
    }
}
