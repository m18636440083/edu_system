package yan.dong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yan.dong.dao.UserMapper;
import yan.dong.domain.*;
import yan.dong.service.UserService;
import yan.dong.utils.Md5;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(Integer id, String status) {

        User user = new User();
        user.setId(id);
        user.setStatus(status);
        if ("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if (user1 != null && Md5.verify(user.getPassword(),"yandong",user1.getPassword())) {
            return user1;
        } else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(int id) {
        return userMapper.findUserRelationRoleById(id);
    }

    @Override
    public void userContextRole(UserVo userVo) {
        userMapper.deleteUserContextRole(userVo.getUserId());
        List<Integer> roleIdList = userVo.getRoleIdList();
        for (Integer roleId : roleIdList) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {

        //1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(id);

        //2.获取角色ID,保存到 list
        List<Integer> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(role.getId());
        }

        //3.根据角色id查询 父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(list);

        //4.封装父菜单下的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        //5.获取资源权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        //6.封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        //menuList: 菜单权限数据
        map.put("resourceList",resourceList);
        //resourceList: 资源权限数据

        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }
}
