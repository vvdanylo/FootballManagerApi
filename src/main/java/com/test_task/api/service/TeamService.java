package com.test_task.api.service;

import com.test_task.api.dto.CreateTeamDto;
import com.test_task.api.dto.ResponseTeamDto;
import com.test_task.api.dto.UpdateTeamDto;
import com.test_task.api.exception.PlayerNotFoundException;
import com.test_task.api.exception.TeamNotFoundException;
import com.test_task.api.exception.TransferPlayerException;

import java.util.List;

public interface TeamService {
    ResponseTeamDto createTeam(CreateTeamDto dto);

    ResponseTeamDto updateTeam(Long id, UpdateTeamDto dto);

    ResponseTeamDto getTeamById(Long id) throws TeamNotFoundException;

    List<ResponseTeamDto> getAllTeams();

    ResponseTeamDto transferPlayer(Long teamId, Long playerId) throws TeamNotFoundException, PlayerNotFoundException, TransferPlayerException;

    void deleteTeam(Long id);
}
