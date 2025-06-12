package org.example.userservice.service.impl;

import org.example.userservice.dto.UserDTO;
import org.example.userservice.entity.User;
import org.example.userservice.repo.UserRepo;
import org.example.userservice.service.UserService;
import org.example.userservice.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveUser(UserDTO userDTO) {
       boolean isExist  =  userRepo.existsByEmail(userDTO.getEmail());

       if (isExist){
           return VarList.Unauthorized;
       }else {
           User user = modelMapper.map(userDTO, User.class);
           userRepo.save(user);
           return VarList.Created;
       }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return modelMapper.map(userRepo.findAll(), new TypeToken<List<UserDTO>>(){}.getType());
    }

    @Override
    public boolean deleteUser(String email) {
        if (userRepo.existsByEmail(email)) {
           User deleteUser =  userRepo.getReferenceByEmail(email);
            userRepo.delete(deleteUser);
            return true;
        } else {
            return false;
        }
    }
}
