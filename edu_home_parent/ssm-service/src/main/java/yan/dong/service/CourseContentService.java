package yan.dong.service;

import yan.dong.domain.Course;
import yan.dong.domain.CourseLesson;
import yan.dong.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*
        根据课程id查询课程对应的章节信息与课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int id);


    /*
        根据课程id查询课程信息
     */
    public Course findCourseByCourseId(int id);

    /*
        新增章节信息
     */
    public void saveSection(CourseSection courseSection);

    /*
        修改章节信息
     */
    public void updateSection(CourseSection courseSection);

    /*
        修改章节状态
     */
    public void updateSectionStatus(int id, int status);

    /*
        新增课时信息
     */
    public void saveLesson(CourseLesson lesson);
}
