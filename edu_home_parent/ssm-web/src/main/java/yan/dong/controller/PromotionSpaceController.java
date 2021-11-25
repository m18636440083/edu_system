package yan.dong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yan.dong.domain.PromotionSpace;
import yan.dong.domain.ResponseResult;
import yan.dong.service.PromotionSpaceService;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;
    /*
        查询所有广告位
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {
        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();

        ResponseResult result = new ResponseResult(true, 200, "广告位查询成功", list);
        return result;
    }

    /*
        新增修改广告位
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {

        if (promotionSpace.getId() == null) {
            promotionSpaceService.savePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true, 200, "广告位添加成功", null);
            return result;
        } else {
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true, 200, "广告位修改成功", null);
            return result;
        }

    }


    /*
        根据id查询广告位
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id) {
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true, 200, "根据id查询成功", promotionSpace);
        return result;
    }
}
