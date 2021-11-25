package yan.dong.service;

import com.github.pagehelper.PageInfo;
import yan.dong.domain.Resource;
import yan.dong.domain.ResourceCategory;
import yan.dong.domain.ResourceVO;

import java.util.List;

public interface ResourceService {

    /*
        资源分页&多条件查询
     */
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO);

    /*
        查询所有资源分类
     */
    public List<ResourceCategory> findAllResourceCategory();
}
