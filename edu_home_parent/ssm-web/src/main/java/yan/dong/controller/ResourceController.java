package yan.dong.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yan.dong.domain.Resource;
import yan.dong.domain.ResourceCategory;
import yan.dong.domain.ResourceVO;
import yan.dong.domain.ResponseResult;
import yan.dong.service.ResourceService;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /*
        资源分页&多条件查询
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO) {
        PageInfo<Resource> allResource = resourceService.findAllResource(resourceVO);
        ResponseResult result = new ResponseResult(true, 200, "资源分页多条件查询成功", allResource);
        return result;
    }

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {
        List<ResourceCategory> allResourceCategory = resourceService.findAllResourceCategory();
        ResponseResult result = new ResponseResult(true, 200, "查询所有资源分类成功", allResourceCategory);
        return result;
    }
}
