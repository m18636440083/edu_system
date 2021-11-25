package yan.dong.service;

import yan.dong.domain.Menu;

import java.util.List;

public interface MenuService {

    /*
        查询全部的父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
        查询所有菜单信息
     */
    public List<Menu> findAllMenu();


    public Menu findMenuById(Integer id);
}
