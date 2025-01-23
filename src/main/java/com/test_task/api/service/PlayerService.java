package com.test_task.api.service;

import com.test_task.api.dto.CreatePlayerDto;
import com.test_task.api.dto.ResponsePlayerDto;
import com.test_task.api.dto.UpdatePlayerDto;
import com.test_task.api.exception.PlayerNotFoundException;

import java.util.List;

public interface PlayerService {
    ResponsePlayerDto createPlayer(CreatePlayerDto dto);

    ResponsePlayerDto updatePlayer(Long id, UpdatePlayerDto dto) throws PlayerNotFoundException;

    ResponsePlayerDto getPlayerById(Long id) throws PlayerNotFoundException;

    List<ResponsePlayerDto> getAllPlayers();

    void deletePlayer(Long id);
}
