package pers.justin.preselectioncourses.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.net.SocketFlow;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.justin.preselectioncourses.entity.CsCourse;
import pers.justin.preselectioncourses.entity.CsSelection;
import pers.justin.preselectioncourses.mapper.CsCourseMapper;
import pers.justin.preselectioncourses.utils.DateUtil;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Justin on 2022-02-13 16:12
 */
@Service
public class CsCourseService {

    @Autowired
    CsCourseMapper csCourseMapper;

    @Autowired
    CsSelectionService csSelectionService;

    private static final String[] WEEK_CN = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};

    /**
     * 添加课程或者修改课程
     * 1.判断该课程的数据是否已存在，若存在则返回冲突信息，否则执行新增课程或修改课程
     * 2.新增课程，新增课程数据，然后将该老师与该课程进行绑定
     * 3.修改课程，其实修改课程会被校验冲突的方法拦截掉；所以这一步可以去到确认覆盖数据的方法执行
     *
     * @param csCourse 课程实体
     */
    public Map<String, Object> addCourse(CsCourse csCourse) throws JsonProcessingException {

        // 判断添加或修改的课程数据是否发生冲突，
        Map<String, Object> conflictCourseMap = checkForConflicts(csCourse);
        if (ObjectUtils.isNotEmpty(conflictCourseMap)) {
            ObjectMapper om = new ObjectMapper();
            // 移除任课老师名称
            String teacherName = (String) conflictCourseMap.remove("teacher");
            CsCourse conflictCourse = om.readValue(om.writeValueAsString(conflictCourseMap), CsCourse.class);
            // 判断是否有冲突课程的数据，有就封装好数据传给前端
            Map<String, Object> respData = new HashMap<>();
            // 补充任课老师的名称
            String msg = "该上课时间段【{0}~{1}】以及上课地点【{2}】已被占用，是否覆盖【{3}】老师的课程？";
            msg = MessageFormat.format(msg,
                    conflictCourse.getClassStartTime(),
                    conflictCourse.getClassEndTime(),
                    conflictCourse.getClassAddress(),
                    teacherName
            );
            respData.put("isConflict", Boolean.TRUE);
            respData.put("conflictMsg", msg);

            return respData;
        }

        // 进行课程新增操作
        if (ObjectUtils.isEmpty(csCourse.getId())) {
            csCourse.setStatus(Boolean.FALSE);
            csCourse.setCreateTime(new Date());
            csCourse.setIsDelete(Boolean.FALSE);
            // 添加课程
            csCourseMapper.insert(csCourse);
            // 绑定任课老师
            csSelectionService.bindUser(CsSelection.builder()
                    .userId(csCourse.getUserId())
                    .couId(csCourse.getId())
                    .createTime(new Date())
                    .build());
        }

        return null;
    }

    /**
     * 查询未结课的课程是否存在数据冲突
     * 1.冲突条件判断：上课地址、上课时间
     *
     * @param csCourse 课程实体
     */
    private Map<String, Object> checkForConflicts(CsCourse csCourse) {

        Map<String, Object> map = csCourseMapper.checkConflictsByClassTimeAndAddress(csCourse);
        if (ObjectUtils.isNotEmpty(map)) {
            // 查询冲突课程的老师名称
            String username = csSelectionService.getUsernameByCourse(csCourse);
            map.put("teacher", username);
        }

        return map;
    }

    /**
     * 确认覆盖冲突的课程数据
     *
     * @param csCourse 课程实体
     */
    public Boolean confirmCoverage(CsCourse csCourse) throws JsonProcessingException {
        Map<String, Object> conflictCourseMap = checkForConflicts(csCourse);
        conflictCourseMap.remove("teacher");
        ObjectMapper om = new ObjectMapper();
        CsCourse conflictCourse = om.readValue(om.writeValueAsString(conflictCourseMap), CsCourse.class);
        // 把原有的数据删掉
        csCourseMapper.deleteByPrimaryKey(conflictCourse.getId());
        // 解绑任课老师与该课程的
        csSelectionService.unbindUser(CsSelection.builder()
                .userId(conflictCourse.getUserId())
                .couId(conflictCourse.getId())
                .build());

        // 进行课程新增操作
        csCourse.setId(null);
        csCourse.setStatus(Boolean.FALSE);
        csCourse.setCreateTime(new Date());
        csCourse.setIsDelete(Boolean.FALSE);
        // 添加课程
        int count = csCourseMapper.insert(csCourse);
        // 绑定任课老师
        csSelectionService.bindUser(CsSelection.builder()
                .userId(csCourse.getUserId())
                .couId(csCourse.getId())
                .createTime(new Date())
                .build());

        return count == 1;
    }

    /**
     * 分页查询
     *
     * @param pageNumber 页码
     * @param pageSize   显示数据
     */
    public PageInfo<Map<String, Object>> queryByPaged(Integer pageNumber, Integer pageSize, String userOrCouName) {

        // 使用PageHelper分页插件
        if (ObjectUtils.isNotEmpty(pageNumber) && ObjectUtils.isNotEmpty(pageSize)) {
            PageHelper.startPage(pageNumber, pageSize);
        }

        List<Map<String, Object>> csCourses = csCourseMapper.queryByPaged(userOrCouName);

        try {
            for (Map<String, Object> map : csCourses) {

                // 统计课程人数
                int selectCourseNumbers = csSelectionService.selectCourseCount((int) map.get("id"));

                boolean status = (boolean) map.get("status");
                // 格式转换，2022-01-01 11:00~12:00
                Date classStartTime = (Date) map.get("classStartTime");
                Date classEndTime = (Date) map.get("classEndTime");
                String startFormatTime = DateUtil.format(classStartTime, DateUtil.exportXlsDateCreateTimeFormat);
                String endFormatTime = DateUtil.format(classEndTime, DateUtil.exportXlsDateCreateTimeFormat);
                String[] start = startFormatTime.split(" ");
                String[] end = endFormatTime.split(" ");
                String startDate = start[0];
                String startHour = start[1];
                String endHour = end[1];
                String time = startDate + " " + startHour + " ~ " + endHour;

                // 算出时间为第几周，星期几
                int day = DateUtil.dayForWeek(startFormatTime);
                String dayStr = null;
                for (int i = 0; i <= WEEK_CN.length; i++) {
                    dayStr = WEEK_CN[day - 1];
                }

                map.put("time", time);
                map.put("weekContent", dayStr);
                map.put("statusCn", status ? "已结课" : "未结课");
                map.put("selectCourseNumbers", selectCourseNumbers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PageInfo.of(csCourses);
    }

    public PageInfo queryByMine(Integer pageNumber, Integer pageSize, String userId) {

        // 使用PageHelper分页插件
        if (ObjectUtils.isNotEmpty(pageNumber) && ObjectUtils.isNotEmpty(pageSize)) {
            PageHelper.startPage(pageNumber, pageSize);
        }

        //List<CsCourse> csCourses = csCourseMapper.queryByUser(userId);
        List<Map<String, Object>> listMyCourses = csCourseMapper.queryByUser2(userId);

        try {
            for (Map<String, Object> map : listMyCourses) {
                boolean status = (boolean) map.get("status");
                // 格式转换，2022-01-01 11:00~12:00
                Date classStartTime = (Date) map.get("classStartTime");
                Date classEndTime = (Date) map.get("classEndTime");
                String startFormatTime = DateUtil.format(classStartTime, DateUtil.exportXlsDateCreateTimeFormat);
                String endFormatTime = DateUtil.format(classEndTime, DateUtil.exportXlsDateCreateTimeFormat);
                String[] start = startFormatTime.split(" ");
                String[] end = endFormatTime.split(" ");
                String startDate = start[0];
                String startHour = start[1];
                String endHour = end[1];
                String time = startDate + " " + startHour + " ~ " + endHour;

                // 算出时间为第几周，星期几
                int day = DateUtil.dayForWeek(startFormatTime);
                String dayStr = null;
                for (int i = 0; i <= WEEK_CN.length; i++) {
                    dayStr = WEEK_CN[day - 1];
                }

                map.put("time", time);
                map.put("weekContent", dayStr);
                map.put("statusCn", status ? "已结课" : "未结课");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return PageInfo.of(listMyCourses);
    }

    /**
     * 删除课程资料
     *
     * @param courseId 课程id
     */
    public Boolean deleteCourse(Integer courseId) {
        return csCourseMapper.deleteByPrimaryKey(courseId) == 1;
    }
}
