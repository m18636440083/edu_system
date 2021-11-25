package yan.dong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yan.dong.domain.Menu;
import yan.dong.domain.ResponseResult;
import yan.dong.service.MenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> allMenu = menuService.findAllMenu();
        ResponseResult result = new ResponseResult(true, 200, "菜单信息查询成功", allMenu);
        return result;
    }

    /*
        回显菜单信息（包括父子菜单的全部信息）
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult findMenuInfoById(@RequestParam Integer id) {
        if (id == -1) {
            List<Menu> menulist = menuService.findSubMenuListByPid(-1);


            // 封装数据
            Map<String, Object> map = new HashMap<>();

            map.put("menuInfo",null);
            map.put("parentMenuList",menulist);
            ResponseResult result = new ResponseResult(true, 200, "添加回显成功", map);
            return result;
        } else {
            Menu menu = menuService.findMenuById(id);
            List<Menu> menulist = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menulist);
            ResponseResult result = new ResponseResult(true, 200, "修改回显成功", map);
            return result;
        }
    }
}
