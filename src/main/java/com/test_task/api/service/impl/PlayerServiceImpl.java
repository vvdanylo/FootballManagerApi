package com.test_task.api.service.impl;

import com.test_task.api.dto.CreatePlayerDto;
import com.test_task.api.dto.ResponsePlayerDto;
import com.test_task.api.dto.UpdatePlayerDto;
import com.test_task.api.exception.PlayerNotFoundException;
import com.test_task.api.mapper.PlayerMapper;
import com.test_task.api.repository.PlayerRepository;
import com.test_task.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public ResponsePlayerDto createPlayer(CreatePlayerDto dto) {
        final var player = PlayerMapper.INSTANCE.createPlayerDtoToPlayer(dto);
        final var savedEntity = playerRepository.save(player);
        return PlayerMapper.INSTANCE.playerToResponsePlayerDto(savedEntity);
    }

    @Override
    public ResponsePlayerDto updatePlayer(Long id, UpdatePlayerDto dto) throws PlayerNotFoundException {
        final var player = playerRepository
                .findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with ID: " + id + " not found"));

        player.setName(dto.getName());
        player.setSurname(dto.getSurname());
        player.setAge(dto.getAge());
        player.setExperienceInMonths(dto.getExperienceInMonths());

        final var savedEntity = playerRepository.save(player);
        return PlayerMapper.INSTANCE.playerToResponsePlayerDto(savedEntity);
    }

    @Override
    public ResponsePlayerDto getPlayerById(Long id) throws PlayerNotFoundException {
        final var player = playerRepository
                .findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with ID: " + id + " not found"));
        return PlayerMapper.INSTANCE.playerToResponsePlayerDto(player);
    }

    @Override
    public List<ResponsePlayerDto> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(PlayerMapper.INSTANCE::playerToResponsePlayerDto)
                .toList();
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
