package cn.dcan.Service;
import cn.dcan.dto.UserDTO;

import java.util.*;

/**
 * Created by dongc_000 on 2018/4/29.
 */
public interface UserService {
    UserDTO verifyUser(int id, byte[] password, int role);
    List<UserDTO> getUserList();
    int addUser(UserDTO userDTO);
}
