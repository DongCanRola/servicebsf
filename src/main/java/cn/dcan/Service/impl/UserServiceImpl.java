package cn.dcan.Service.impl;

import cn.dcan.Service.UserService;
import cn.dcan.dto.UserDTO;
import cn.dcan.entity.User;
import cn.dcan.entity.UserRoleKey;
import cn.dcan.mapper.UserMapper;
import cn.dcan.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongc_000 on 2018/4/29.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public UserDTO verifyUser(int id, byte[] password, int role) {
        User user = userMapper.verifyUser(id, password);
        ArrayList<Integer> roles = userRoleMapper.selectByUser(id);
        int s = roles.size();
        if(user != null) {
            if(s == 0) {
                System.out.println("There is no role of the user!");
            } else {
                for(int i = 0; i < s; i++) {
                    if(role == roles.get(i)) {
                        return entityToDto(user,roles);
                    }
                }
                System.out.println("The user does not match the role!");
            }
        } else {
            System.out.println("Cannot find the user!");
        }
        return null;
    }

    @Override
    public List<UserDTO> getUserList() {
        List<User> users = userMapper.getUserList();
        List<UserDTO> result = new ArrayList<>();
        for(User user:users) {
            ArrayList<Integer> role = userRoleMapper.selectByUser(user.getId());
            result.add(entityToDto(user,role));
        }
        return result;
    }

    @Override
    public int addUser(UserDTO userDTO) {
        User user = dtoToEntity(userDTO);
        int count = userMapper.insertSelective(user);
        System.out.println("new user id:" + user.getId());
        List<UserRoleKey> userRoleKeyList = getRoles(user.getId(), userDTO.getUser_roles());
        for(UserRoleKey userRoleKey : userRoleKeyList) {
            int test = userRoleMapper.insert(userRoleKey);
            System.out.println("insert user role result: " + test);
        }
        return user.getId();
    }

    @Override
    public int modifyUserPassword(int userId, byte[] oldPassword, byte[] newPassword) {
        User user = userMapper.verifyUser(userId, oldPassword);
        if(user != null) {
            user.setPassword(newPassword);
            return userMapper.updateByPrimaryKeySelective(user);
        }
        return 0;
    }

    private User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        if(userDTO.getUser_name() != null) {
            user.setName(userDTO.getUser_name());
        }
        if(userDTO.getPhone() != null) {
            user.setPhone(userDTO.getPhone());
        }
        if(userDTO.getQqnumber() != null) {
            user.setQqnumber(userDTO.getQqnumber());
        }
        if(userDTO.getWechat() != null) {
            user.setWechat(userDTO.getWechat());
        }
        if(userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword().getBytes());
        }
        return user;
    }

    private List<UserRoleKey> getRoles(int userId, ArrayList<Integer> roles) {
        List<UserRoleKey> result = new ArrayList<>();
        UserRoleKey userRoleKey = new UserRoleKey();
        for(int roleId : roles) {
            userRoleKey.setUserid(userId);
            userRoleKey.setRoleid(roleId);
            result.add(userRoleKey);
        }
        return result;
    }

    private UserDTO entityToDto(User user, ArrayList<Integer> roles) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getId());
        userDTO.setUser_name(user.getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setQqnumber(user.getQqnumber());
        userDTO.setWechat(user.getWechat());
        userDTO.setEmail(user.getEmail());
        userDTO.setUser_roles(roles);
        return userDTO;
    }
}
