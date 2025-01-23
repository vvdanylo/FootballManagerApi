package com.test_task.api.command_line_runner;

import com.test_task.api.entity.Player;
import com.test_task.api.entity.Team;
import com.test_task.api.repository.PlayerRepository;
import com.test_task.api.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class MockDataLoader implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MockDataLoader.class);

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public MockDataLoader(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (playerRepository.count() > 0 || teamRepository.count() > 0) {
            logger.info("Data already exists, skipping initialization.");
            return;
        }

        Team team1 = Team.builder()
                .name("Team Alpha")
                .transferCommission(0.05)
                .balance(BigDecimal.valueOf(100000))
                .build();

        Team team2 = Team.builder()
                .name("Team Beta")
                .transferCommission(0.07)
                .balance(BigDecimal.valueOf(200000))
                .build();

        teamRepository.saveAll(Arrays.asList(team1, team2));

        Player player1 = Player.builder()
                .name("John")
                .surname("Doe")
                .age(25)
                .experienceInMonths(36)
                .team(team1)
                .build();

        Player player2 = Player.builder()
                .name("Alice")
                .surname("Smith")
                .age(23)
                .experienceInMonths(24)
                .team(team1)
                .build();

        Player player3 = Player.builder()
                .name("Bob")
                .surname("Brown")
                .age(27)
                .experienceInMonths(48)
                .team(team1)
                .build();

        Player player4 = Player.builder()
                .name("Charlie")
                .surname("Davis")
                .age(22)
                .experienceInMonths(12)
                .team(team1)
                .build();

        Player player5 = Player.builder()
                .name("Emma")
                .surname("Wilson")
                .age(26)
                .experienceInMonths(30)
                .team(team1)
                .build();

        Player player6 = Player.builder()
                .name("Liam")
                .surname("Anderson")
                .age(28)
                .experienceInMonths(60)
                .team(team2)
                .build();

        Player player7 = Player.builder()
                .name("Olivia")
                .surname("Martinez")
                .age(21)
                .experienceInMonths(18)
                .team(team2)
                .build();

        Player player8 = Player.builder()
                .name("Noah")
                .surname("Taylor")
                .age(29)
                .experienceInMonths(72)
                .team(team2)
                .build();

        Player player9 = Player.builder()
                .name("Ava")
                .surname("White")
                .age(24)
                .experienceInMonths(36)
                .team(team2)
                .build();

        Player player10 = Player.builder()
                .name("Sophia")
                .surname("Moore")
                .age(23)
                .experienceInMonths(24)
                .team(team2)
                .build();

        playerRepository.saveAll(Arrays.asList(player1, player2, player3, player4, player5, player6, player7, player8, player9, player10));

        logger.info("Mock data has been initialized.");
    }
}
