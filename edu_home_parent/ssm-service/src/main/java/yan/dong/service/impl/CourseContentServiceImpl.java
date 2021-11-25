package yan.dong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yan.dong.dao.CourseContentMapper;
import yan.dong.domain.Course;
import yan.dong.domain.CourseLesson;
import yan.dong.domain.CourseSection;
import yan.dong.service.CourseContentService;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int id) {
        return courseContentMapper.findSectionAndLessonByCourseId(id);
    }

    @Override
    public Course findCourseByCourseId(int id) {
        return courseContentMapper.findCourseByCourseId(id);
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {

        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveLesson(CourseLesson lesson) {
        //补全信息
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);
        courseContentMapper.saveLesson(lesson);
    }

}
