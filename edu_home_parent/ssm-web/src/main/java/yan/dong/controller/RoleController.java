package yan.dong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yan.dong.domain.Menu;
import yan.dong.domain.ResponseResult;
import yan.dong.domain.Role;
import yan.dong.domain.RoleMenuVo;
import yan.dong.service.MenuService;
import yan.dong.service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> list = roleService.findAllRole(role);

        ResponseResult result = new ResponseResult(true, 200, "角色列表查询成功", list);

        return result;
    }

    /*
        查询所有的父子菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", subMenuListByPid);

        ResponseResult result = new ResponseResult(true, 200, "父子菜单信息查询成功", map);
        return result;
    }

    /*
        添加或者更新角色信息
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {
        if (role.getId() == null) {
            roleService.saveRole(role);
            ResponseResult result = new ResponseResult(true, 200, "角色信息添加成功", null);
            return result;
        } else {
            roleService.update(role);
            ResponseResult result = new ResponseResult(true, 200, "角色信息更新成功", null);
            return result;
        }

    }

    /*
        查询角色关联菜单列表ID
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {
        List<Integer> menuList = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true,200,"响应成功",menuList);
        return result;
    }

    /*
        用户关联菜单 {roleId: 4, menuIdList: [19, 20, 7, 8, 9, 15, 16, 17, 18]}
    */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.RoleContextMenu(roleMenuVo);
        ResponseResult result = new ResponseResult(true,200,"响应成功","");
        return result;
    }

    /*
        删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {
        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true, 200, "删除角色成功", null);
        return result;
    }

}