package com.test_task.api.controller;

import com.test_task.api.dto.CreateTeamDto;
import com.test_task.api.dto.UpdateTeamDto;
import com.test_task.api.dto.ResponseTeamDto;
import com.test_task.api.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseTeamDto> create(@RequestBody @Valid CreateTeamDto dto) {
        final var team = teamService.createTeam(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }

    @PostMapping("/transfer-player")
    public ResponseEntity<ResponseTeamDto> addPlayer(
            @RequestParam("team_id") Long teamId,
            @RequestParam("player_id") Long playerId
    ) {
        final var team = teamService.transferPlayer(teamId, playerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseTeamDto> getById(@PathVariable Long id) {
        final var team = teamService.getTeamById(id);
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ResponseTeamDto>> getAll() {
        final var teams = teamService.getAllTeams();
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseTeamDto> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateTeamDto dto
    ) {
        final var teams = teamService.updateTeam(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseTeamDto> delete(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
