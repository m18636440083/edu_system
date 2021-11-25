package yan.dong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yan.dong.domain.Course;
import yan.dong.domain.CourseLesson;
import yan.dong.domain.CourseSection;
import yan.dong.domain.ResponseResult;
import yan.dong.service.CourseContentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer id) {
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(id);
        ResponseResult result = new ResponseResult(true, 200, "课程及其关联信息显示成功", list);

        return result;
    }


    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "课程信息回显成功", course);

        return result;

    }


    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {

        if (courseSection.getId() == null) {
            courseContentService.saveSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "章节信息添加成功", null);
            return result;
        } else {
            courseContentService.updateSection(courseSection);
            ResponseResult result = new ResponseResult(true, 200, "章节信息修改成功", null);
            return result;
        }
    }


    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status) {
        courseContentService.updateSectionStatus(id, status);

        Map<String, Integer> map = new HashMap<>();

        map.put("status", status);

        ResponseResult result = new ResponseResult(true, 200, "章节状态修改成功", map);
        return result;

    }
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson) {
        courseContentService.saveLesson(lesson);
        ResponseResult result = new ResponseResult(true, 200, "课时信息添加成功", null);

        return result;
    }
}
