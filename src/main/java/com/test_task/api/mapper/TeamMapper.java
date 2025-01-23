package com.test_task.api.mapper;

import com.test_task.api.dto.CreateTeamDto;
import com.test_task.api.dto.ResponsePlayerDto;
import com.test_task.api.dto.ResponseTeamDto;
import com.test_task.api.entity.Player;
import com.test_task.api.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    ResponseTeamDto teamToResponseTeamDto(Team team);

    Team createTeamDtoToTeam(CreateTeamDto dto);

    @Mapping(target = "teamName", source = "team.name")
    ResponsePlayerDto playerToResponsePlayerDto(Player player);
}

