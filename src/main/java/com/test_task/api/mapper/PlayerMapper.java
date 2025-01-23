package com.test_task.api.mapper;

import com.test_task.api.dto.CreatePlayerDto;
import com.test_task.api.dto.ResponsePlayerDto;
import com.test_task.api.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(source = "team.name", target = "teamName")
    ResponsePlayerDto playerToResponsePlayerDto(Player player);

    @Mapping(target = "team", ignore = true)
    Player createPlayerDtoToPlayer(CreatePlayerDto dto);
}
