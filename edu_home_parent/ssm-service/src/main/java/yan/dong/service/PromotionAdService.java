package yan.dong.service;

import com.github.pagehelper.PageInfo;
import yan.dong.domain.PromotionAd;
import yan.dong.domain.PromotionAdVO;

public interface PromotionAdService {

    /*
        分页获取所有的广告列表
     */
    public PageInfo  findAllAdByPage(PromotionAdVO promotionAdVO);

    /*
        新增广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /*
        更新广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);


    /*
        回显广告信息
    */
    public PromotionAd findPromotionAdById(int id);

    /*
        更新广告状态信息
     */
    public void updatePromotionAdStatus(int id, int status);
}
