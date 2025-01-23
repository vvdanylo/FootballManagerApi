package com.test_task.api.controller;

import com.test_task.api.dto.CreatePlayerDto;
import com.test_task.api.dto.UpdatePlayerDto;
import com.test_task.api.dto.ResponsePlayerDto;
import com.test_task.api.service.PlayerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponsePlayerDto> create(@RequestBody @Valid CreatePlayerDto dto) {
        final var player = playerService.createPlayer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ResponsePlayerDto>> getAll() {
        final var players = playerService.getAllPlayers();
        return ResponseEntity.status(HttpStatus.OK).body(players);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponsePlayerDto> getById(@PathVariable("id") Long id) {
        final var player = playerService.getPlayerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponsePlayerDto> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdatePlayerDto dto
    ) {
        final var player = playerService.updatePlayer(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CreatePlayerDto> delete(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
