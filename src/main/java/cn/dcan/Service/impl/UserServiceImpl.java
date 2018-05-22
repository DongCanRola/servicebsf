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

    @Override
    public int modifyUserMessage(UserDTO userDTO) {
        User user = dtoToEntity(userDTO);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int modifyUserRole(UserDTO userDTO) {
        int userId = userDTO.getUser_id();
        List<UserRoleKey> userRoleKeyList = getRoles(userId, userDTO.getUser_roles());
        for(int i = 0; i <userRoleKeyList.size(); i++) {
            System.out.println(userRoleKeyList.get(i).getUserid() + "," + userRoleKeyList.get(i).getRoleid());
        }
        //删除原有职责
        int deleteResult = userRoleMapper.deleteByUser(userId);
        if(deleteResult > 0) {
            //插入新职责
            boolean result = true;
            int updateResult = 0;
            for(UserRoleKey userRoleKey : userRoleKeyList) {
                System.out.println("now insert role: "+userRoleKey.getRoleid());
                updateResult += userRoleMapper.insert(userRoleKey);
                if(updateResult <= 0) {
                    result = false;
                    break;
                }
            }
            if(result)
                return updateResult;
            else
                return -1;
        } else
            return -1;
        /*
        if(deleteResult > 0) {

        }else
            return -1;
         */
    }

    @Override
    public int deleteUserRole(int userId) {
        List<Integer> roles = userRoleMapper.selectByUser(userId);
        int deleteResult = 0;
        for(Integer i : roles) {
            UserRoleKey userRoleKey = new UserRoleKey();
            userRoleKey.setUserid(userId);
            userRoleKey.setRoleid(i);
            int cuRe = userRoleMapper.deleteByPrimaryKey(userRoleKey);
            if(cuRe > 0)
                deleteResult ++;
            else {
                return -1;
            }
        }
        roles = userRoleMapper.selectByUser(userId);
        for(Integer i: roles) {
            System.out.println(i);
        }
        System.out.println("userrole " + userId + " delete result: "+ deleteResult);
        return deleteResult;
    }

    private User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        if(userDTO.getUser_id() != 0) {
            user.setId(userDTO.getUser_id());
        }
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
        for(int roleId : roles) {
            UserRoleKey userRoleKey = new UserRoleKey();
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
