package cn.dcan.Service;
import cn.dcan.dto.UserDTO;

import java.util.*;

/**
 * Created by dongc_000 on 2018/4/29.
 */
public interface UserService {
    public UserDTO verifyUser(int id, byte[] password, int role);
    public List<UserDTO> getUserList();
    public int addUser(UserDTO userDTO);
}
