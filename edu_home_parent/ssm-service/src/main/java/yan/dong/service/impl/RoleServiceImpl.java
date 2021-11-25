package yan.dong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yan.dong.dao.RoleMapper;
import yan.dong.domain.Role;
import yan.dong.domain.RoleMenuVo;
import yan.dong.domain.Role_menu_relation;
import yan.dong.service.RoleService;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public void saveRole(Role role) {
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        roleMapper.saveRole(role);
    }

    @Override
    public void update(Role role) {
        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        return roleMapper.findMenuByRoleId(roleid);
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        // 清空中间表
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer id) {
        // 清空中间表
        roleMapper.deleteRoleContextMenu(id);
        roleMapper.deleteRole(id);
    }

}
