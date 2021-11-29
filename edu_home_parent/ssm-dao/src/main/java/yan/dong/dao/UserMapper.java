package yan.dong.dao;

import yan.dong.domain.*;

import java.util.List;

public interface UserMapper {

    /*
        查询所有用户
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /*
        修改用户状态
     */
    public void updateUserStatus(User user);

    /*
        用户登录
     */
    public User login(User user);


    /*
        根据用户ID清空中间表
     */
    public void deleteUserContextRole(Integer userId);

    /*
        分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /**
     * 动态菜单展示
     * @param id
     * @return
     */
    /*
        1.根据用户ID查询当前角色所关联的用户信息  角色分配（回显）
    */
    public List<Role> findUserRelationRoleById(int id);

    /*
        2.根据角色ID，查询角色拥有的顶级菜单信息
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
        3.根据父菜单ID查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(int pid);

    /*
        获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);
    
    /*
        演示拉取操作
    */
    public List<Resource> findResourceByRoleId2(List<Integer> ids);
    
    
    public void test1();
    public void test2();
    public void test3();
    public void test4();
    public void test5();
    public void test6();
    public void test7();
    public void test8();  
}
