package yan.dong.dao;

import yan.dong.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {


    /*
        所有广告位查询
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /*
        新增广告位
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /*
        根据id查询广告位
     */
    public PromotionSpace findPromotionSpaceById(int id);


    /*
        修改广告位
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);




}