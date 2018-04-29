package cn.dcan.Service;
import cn.dcan.dto.UserDTO;

/**
 * Created by dongc_000 on 2018/4/29.
 */
public interface UserService {
    public UserDTO verifyUser(int id, byte[] password, int role);
}
