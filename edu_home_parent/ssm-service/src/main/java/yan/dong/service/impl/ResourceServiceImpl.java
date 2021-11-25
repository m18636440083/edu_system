package yan.dong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yan.dong.dao.ResourceMapper;
import yan.dong.domain.Resource;
import yan.dong.domain.ResourceCategory;
import yan.dong.domain.ResourceVO;
import yan.dong.service.ResourceService;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO) {

        PageHelper.startPage(resourceVO.getCurrentPage(), resourceVO.getPageSize());
        List<Resource> allResource = resourceMapper.findAllResource(resourceVO);
        PageInfo<Resource> resourcePageInfo = new PageInfo<>(allResource);

        return resourcePageInfo;
    }

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceMapper.findAllResourceCategory();
    }
}
