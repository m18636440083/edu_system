package yan.dong.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yan.dong.domain.ResponseResult;
import yan.dong.domain.Role;
import yan.dong.domain.User;
import yan.dong.domain.UserVo;
import yan.dong.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {
        PageInfo allUserByPage = userService.findAllUserByPage(userVo);
        ResponseResult result = new ResponseResult(true, 200, "用户分页查询成功", allUserByPage);

        return result;
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam String status) {
        userService.updateUserStatus(id, status);
        if ("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        ResponseResult result = new ResponseResult(true, 200, "用户状态信息更新成功", status);
        return result;
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);
        if (login != null) {
            Map<String, Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token",access_token);
            map.put("user_id",login.getId());
            HttpSession session = request.getSession();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id",login.getId());
            return new ResponseResult(true, 200, "登录成功", map);
        } else {
            return new ResponseResult(true, 400, "用户名或密码错误", null);
        }

    }

    /*
        根据用户ID查询当前角色所关联的用户信息  角色分配（回显）
    */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id) {
        List<Role> userRelationRoleById = userService.findUserRelationRoleById(id);
        return new ResponseResult(true, 200, "分配角色回显成功", userRelationRoleById);
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {
        userService.userContextRole(userVo);
        return new ResponseResult(true, 200, "分配角色成功", null);
    }

    /*
        获取用户权限
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        //获取请求头中的 token
        String token = request.getHeader("Authorization");

        //获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String)session.getAttribute("access_token");

        //判断
        if(token.equals(access_token)){
            int user_id = (Integer)session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else{
            ResponseResult result = new ResponseResult(false,400,"获取失败","");
            return result;
        }
    }

}
