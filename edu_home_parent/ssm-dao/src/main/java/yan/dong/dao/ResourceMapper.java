package yan.dong.dao;

import yan.dong.domain.Resource;
import yan.dong.domain.ResourceCategory;
import yan.dong.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    /*
        资源分页&多条件查询
     */

    public List<Resource> findAllResource(ResourceVO resourceVO);

    /*
        查询所有资源分类
     */
    public List<ResourceCategory> findAllResourceCategory();
}
