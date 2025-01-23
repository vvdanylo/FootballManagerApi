package com.test_task.api.service.impl;

import com.test_task.api.dto.CreateTeamDto;
import com.test_task.api.dto.ResponsePlayerDto;
import com.test_task.api.dto.ResponseTeamDto;
import com.test_task.api.dto.UpdateTeamDto;
import com.test_task.api.entity.Player;
import com.test_task.api.exception.PlayerNotFoundException;
import com.test_task.api.exception.TeamNotFoundException;
import com.test_task.api.exception.TransferPlayerException;
import com.test_task.api.mapper.TeamMapper;
import com.test_task.api.repository.PlayerRepository;
import com.test_task.api.repository.TeamRepository;
import com.test_task.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public ResponseTeamDto createTeam(CreateTeamDto dto) {
        final var team = TeamMapper.INSTANCE.createTeamDtoToTeam(dto);
        team.setPlayers(List.of());
        final var savedEntity = teamRepository.save(team);
        return TeamMapper.INSTANCE.teamToResponseTeamDto(savedEntity);
    }

    @Override
    public ResponseTeamDto updateTeam(Long id, UpdateTeamDto dto) {
        final var team = teamRepository
                .findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team with ID: " + id + " not found"));

        team.setName(dto.getName());
        team.setTransferCommission(dto.getTransferCommission());
        team.setBalance(dto.getBalance());

        final var updatedEntity = teamRepository.save(team);
        return TeamMapper.INSTANCE.teamToResponseTeamDto(updatedEntity);
    }

    @Override
    public ResponseTeamDto getTeamById(Long id) throws TeamNotFoundException {
        final var team = teamRepository
                .findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team with ID: " + id + " not found"));
        return TeamMapper.INSTANCE.teamToResponseTeamDto(team);
    }

    @Override
    public List<ResponseTeamDto> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(TeamMapper.INSTANCE::teamToResponseTeamDto)
                .toList();
    }

    @Override
    public ResponseTeamDto transferPlayer(Long teamId, Long playerId) throws TeamNotFoundException, PlayerNotFoundException {
        final var team = teamRepository
                .findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team with ID: " + teamId + " not found"));

        final var player = playerRepository
                .findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with ID: " + playerId + " not found"));

        if (team.getPlayers().contains(player)) {
            throw new TransferPlayerException("Player with ID: " + playerId + " is already in this team");
        }

        final var totalTransferCost = calculateTotalTransferCost(player);

        if (team.getBalance().compareTo(totalTransferCost) < 0) {
            throw new TransferPlayerException(
                    "Team does not have enough money to transfer player" +
                    "(Team balance: " + team.getBalance() +
                    ", Player transfer cost: " + totalTransferCost + ")"
            );
        }

        increaseCurrentTeamBalance(player, totalTransferCost);
        team.setBalance(team.getBalance().subtract(totalTransferCost));

        player.setTeam(team);
        team.getPlayers().add(player);

        teamRepository.save(team);
        playerRepository.save(player);

        return TeamMapper.INSTANCE.teamToResponseTeamDto(team);
    }


    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    private static BigDecimal calculateTotalTransferCost(Player player) {
        final var currentPlayerTeam = player.getTeam();
        final var transferCost = player.getExperienceInMonths() * 100000 / player.getAge();
        final var transferCommission = currentPlayerTeam != null
                ? currentPlayerTeam.getTransferCommission() * transferCost
                : 0;
        return BigDecimal.valueOf(transferCost + transferCommission);
    }

    private static void increaseCurrentTeamBalance(Player player, BigDecimal totalTransferCost) {
        final var currentPlayerTeam = player.getTeam();
        if (currentPlayerTeam != null) {
            final var currentBalance = currentPlayerTeam.getBalance();
            final var updatedBalance = currentBalance.add(totalTransferCost);
            currentPlayerTeam.setBalance(updatedBalance);
        }
    }
}
