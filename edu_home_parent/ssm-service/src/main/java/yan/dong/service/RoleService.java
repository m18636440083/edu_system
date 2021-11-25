package yan.dong.service;

import yan.dong.domain.Role;
import yan.dong.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

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

    public void update(Role role);

    /*
        根据角色ID查询该角色关联的菜单信息ID [1,2,3,5]
    */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
        为角色分配菜单信息
     */
    public void RoleContextMenu(RoleMenuVo roleMenuVo);


    /*
        删除角色
     */
    public void deleteRole(Integer id);
}
