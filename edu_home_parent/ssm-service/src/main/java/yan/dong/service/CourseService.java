package yan.dong.service;

import yan.dong.domain.Course;
import yan.dong.domain.CourseVO;
import yan.dong.domain.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
        多条件课程列表查询
    */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
        保存课程信息和讲师信息
     */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
        根据id进行课程关联信息查询
     */
    public CourseVO findCourseById(int id);

    /*
        修改课程信息
    */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
        变更课程状态信息
     */
    public void updateCourseStatus(int courseid, int status);



}
