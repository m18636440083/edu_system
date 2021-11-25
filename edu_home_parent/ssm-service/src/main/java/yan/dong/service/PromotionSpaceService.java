package yan.dong.service;

import yan.dong.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {


    /*
        查询所有广告位
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /*
        新增广告位
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /*
        按照id查询广告位
     */
    public PromotionSpace findPromotionSpaceById(int id);

    /*
        修改广告位
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
