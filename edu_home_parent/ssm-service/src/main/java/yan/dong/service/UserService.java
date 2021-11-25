package yan.dong.service;

import com.github.pagehelper.PageInfo;
import yan.dong.domain.ResponseResult;
import yan.dong.domain.Role;
import yan.dong.domain.User;
import yan.dong.domain.UserVo;

import java.util.List;

public interface UserService {

    /*
        分页查询所有用户
     */
    public PageInfo findAllUserByPage(UserVo userVo);


    /*
        修改用户状态
     */
    public void updateUserStatus(Integer id, String status);

    /*
        用户登录
     */
    public User login(User user) throws Exception;

    /*
        根据用户ID查询当前角色所关联的用户信息  角色分配（回显）
    */
    public List<Role> findUserRelationRoleById(int id);

    /*
        用户关联角色
     */
    public void userContextRole(UserVo userVo);

    /*
        获取用户权限
     */
    ResponseResult getUserPermissions(Integer id);
}
