package yan.dong.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import yan.dong.domain.PromotionAd;
import yan.dong.domain.PromotionAdVO;
import yan.dong.domain.ResponseResult;
import yan.dong.service.PromotionAdService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVO) {
        PageInfo allAdByPage = promotionAdService.findAllAdByPage(promotionAdVO);
        ResponseResult result = new ResponseResult(true, 200, "分页查询成功", allAdByPage);
        return result;
    }


    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断文件是否为空
        if(file.isEmpty()) {
            throw new RuntimeException();
        }

        //2.获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String webappPath = realPath.substring(0, realPath.indexOf("ssm-web"));

        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();

        //4.新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.上传文件
        String uploadPath = webappPath + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在就创建项目
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);

        }

        file.transferTo(filePath);

        //6.将文件名和文件路径返回
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080"+"/upload/"+newFileName);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);

        return result;
    }


    /*
        广告的新增与修改
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        if (promotionAd.getId() == null) {
            promotionAdService.savePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "广告新增成功", null);
            return result;
        } else {
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "广告修改成功", null);
            return result;
        }
    }

    /*
        根据id回显广告数据
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id) {
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);

        ResponseResult result = new ResponseResult(true, 200, "广告信息回显成功", promotionAdById);
        return result;
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id, @RequestParam int status) {
        if (status == 1) {
            promotionAdService.updatePromotionAdStatus(id, status);

        } else {
            promotionAdService.updatePromotionAdStatus(id, 0);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);
        ResponseResult result = new ResponseResult(true, 200, "广告状态修改成功", map);
        return result;
    }

}
