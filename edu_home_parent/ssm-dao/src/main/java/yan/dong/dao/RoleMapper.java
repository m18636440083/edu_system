package yan.dong.dao;

import yan.dong.domain.Role;
import yan.dong.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*
        查询角色列表
     */
    public List<Role> findAllRole(Role role);

    /*
        添加角色信息
     */
    public void saveRole(Role role);

    /*
        更新角色信息
     */
    public void updateRole(Role role);


    /*
        根据角色ID查询该角色关联的菜单信息ID [1,2,3,5]
    */
    public List<Integer> findMenuByRoleId(Integer roleid);


    /*
        根据roleid清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);


    /*
        为角色分配菜单信息
     */

    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */

    public void deleteRole(Integer roleid);

}
