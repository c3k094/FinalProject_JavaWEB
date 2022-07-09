package PETVET.bg.petvet.model.mapper;

import PETVET.bg.petvet.model.dto.UserRegisterDTO;
import PETVET.bg.petvet.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "active", constant = "true")
  UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);
}
