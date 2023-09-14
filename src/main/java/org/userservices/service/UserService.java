package org.userservices.service;

import org.userservices.payload.PostResponse;
import org.userservices.payload.UserDto;

import java.util.List;

public interface UserService {

    void saveUserDetail(UserDto dto);

    UserDto getById(String id);

    PostResponse getAll(Integer pageNumber , Integer pageSize, String sortBy, String sortDir);

    void updateById(String id, UserDto userDto);

    UserDto deleteUserById(String id);
}
