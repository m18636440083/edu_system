package yan.dong.dao;

import yan.dong.domain.Course;
import yan.dong.domain.CourseLesson;
import yan.dong.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /*
        查询课程下的章节与课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseid);

    /*
        根据课程id查询课程信息
     */
    public Course findCourseByCourseId(int courseId);

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
    public void updateSectionStatus(CourseSection courseSection);

    /*
        新增课时信息
     */
    public void saveLesson(CourseLesson courseLesson);
}
