package org.userservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.userservices.entities.User;
import org.userservices.exceptions.ResourceNotFoundException;
import org.userservices.payload.PostResponse;
import org.userservices.payload.UserDto;
import org.userservices.repositories.UserRepository;
import org.userservices.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepo;
    @Override
    public void saveUserDetail(UserDto dto) {
        User user = mapToEntity(dto);
        userRepo.save(user);
    }

    @Override
    public UserDto getById(String id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("resource is not found to this id" + id)
        );
        UserDto userDto = mapToDto(user);

        return userDto;
    }



    @Override
    public PostResponse getAll(Integer pageNumber, Integer pageSize , String sortBy, String sortDir) {

        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")){
             sort = Sort.by(sortBy).ascending();
       }
       else {
             sort = Sort.by(sortBy).descending();
       }
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<User> users = userRepo.findAll(pageable);
        List<User> allUser = users.stream().map(user -> user).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();

        //   List<User> allUser = userRepo.findAll();
//        List<UserDto> userDtos = new ArrayList<>();
//        for(User user: allUser){
//            UserDto userDto = mapToDto(user);
//            userDtos.add(userDto);
//        }
        // ** Another method
       List<UserDto> userDtos = allUser.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        postResponse.setContent(userDtos);
        postResponse.setPageNumber(users.getNumber());
        postResponse.setPageSize(users.getSize());
        postResponse.setTotalElement(users.getTotalElements());
        postResponse.setLastPage(users.isLast());
        return postResponse;
    }

    @Override
    public void updateById(String id, UserDto userDto) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found by this is " + id)
        );
        user.setMobile(userDto.getMobile());
        user.setUserId(userDto.getUserId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userRepo.save(user);
    }

    @Override
    public UserDto deleteUserById(String id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found by this is " + id)
        );
        userRepo.deleteById(id);
        UserDto userDto = mapToDto(user);
        return userDto;
    }

    User mapToEntity(UserDto dto){
        User u = new User();
        u.setUserId(dto.getUserId());
        u.setFirstName(dto.getFirstName());
        u.setLastName(dto.getLastName());
        u.setEmail(dto.getEmail());
        u.setMobile(dto.getMobile());
        return u;
    }
    UserDto mapToDto(User user){
        UserDto userDto= new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setMobile(user.getMobile());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}
