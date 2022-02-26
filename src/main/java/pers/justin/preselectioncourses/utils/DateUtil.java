package pers.justin.preselectioncourses.utils;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: 时间工具类
 */
public abstract class DateUtil {

    /*
     * mode(模式) (受次影响的是getStringOfDate()方法) 第一种,返回正常的date的字符串(默认的)
     * 第二种,返回date的当天的开始时间 第三种,返回date的当天的结束时间
     */
    public final static String defaultMode = "normalOfDate";

    public final static String beginOfDateMode = "beginOfDate";

    public final static String endOfDateMode = "endOfDate";

    /**
     * 一天的秒数
     */
    public final static int DAY_SECOND = 24 * 60 * 60;
    /**
     * 一天的毫秒数
     */
    public final static long DAY_MILLISECOND = DAY_SECOND * 1000;

    /**
     * @Fields MINUTE_MILLISECOND : 一分钟的毫秒数
     */
    public final static long MINUTE_MILLISECOND = 60 * 1000;

    /**
     * 默认的日期格式字符串 yyyy-MM-dd
     */
    public final static String exportXlsDateCreateTimeFormat = "yyyy-MM-dd HH:mm";// 创建时间，由于后台几乎所有的创建时间都是用这种形式展示，故设一常量
    public final static String exportXlsDateFormat = "yyyy.MM.dd";// 导出xls文件的文件名中时间部分格式
    public final static String defaultDatePatternStr = "yyyy-MM-dd";
    public final static String chineseDatePatternStr = "yyyy年MM月dd日";
    /**
     * 默认的日期时间格式字符串 yyyy-MM-dd HH:mm:ss
     */
    public final static String defaultDateTimePatternStr = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认的长日期时间格式字符串 yyyy-MM-dd HH:mm:ss:SSS
     */
    public final static String defaultLongDateTimePatternStr = "yyyy-MM-dd HH:mm:ss:SSS";

    public static final String DENSEDATETIMEPATTERN = "yyyyMMddHHmmss";

    public static final String YYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    public static final String HHMMSS_PATTERN = "HH:mm:ss";

    public enum DatePart {
        /**
         * 年
         */
        yy,
        /**
         * 月
         */
        MM,
        /**
         * 日
         */
        dd,
        /**
         * 时
         */
        HH,
        /**
         * 分
         */
        mm,
        /**
         * 秒
         */
        ss,
        /**
         * 毫秒
         */
        SSS
    }

    /**
     * 把Date.gettime()的值还原回Date
     *
     * @param timeString
     * @return
     */
    public static Date getByTime(String timeString) {
        Date date = null;
        if (!StringUtil.isNullNEmpty(timeString))
            date = new Date(Long.valueOf(timeString));
        else
            date = new Date();
        return date;
    }

    /**
     * 把字符串格式化成日期类型("yyyy-MM-dd HH:mm:ss")
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date convertStr2Date(String dateStr) throws ParseException {
        return parse(dateStr, defaultDateTimePatternStr);
    }

    /**
     * 把字符串格式化成日期类型
     *
     * @param dateStr
     * @param pattern 日期格式，为空时默认为"yyyy-MM-dd HH:mm:ss"格式
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        if (pattern == null)
            pattern = defaultDateTimePatternStr;
        if (dateStr == null) {
            throw new ParseException("dateStr不能为空", 0);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(dateStr);
        return date;
    }

    /**
     * 将Date类型的日期转换成相应类型的字符串
     *
     * @param date
     * @return
     */
    public static String convertDate2Str(Date date, String sf) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sf);
        return simpleDateFormat.format(date);
    }

    /**
     * 将Date类型的日期转换成"yyyy-MM-dd HH:mm:ss"类型的字符串
     *
     * @param date
     * @return
     */
    public static String convertDate2Str(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimePatternStr);
        return simpleDateFormat.format(date);
    }

    /**
     * 将Date类型的日期转换成指定格式的字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null)
            return null;
        if (pattern == null)
            pattern = defaultDateTimePatternStr;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取指定日期的开始时间
     *
     * @param date
     * @return
     */
    public static Date getBeginOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 获取指定日期的最后时间
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.MINUTE, 59);
        cld.set(Calendar.SECOND, 59);
        cld.set(Calendar.MILLISECOND, 000);
        return cld.getTime();
    }

    /**
     * 方法用途和描述: 取得当前时间的字符串 例如：20080221050416
     *
     * @return
     */
    public static String getCurrentTimeStr() {
        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 方法用途和描述: 取得当前时间的字符串 例如：20080221050416
     *
     * @return
     */
    public static String getCurrentTimeStr(String sf) {
        return format(new Date(), sf);
    }

    /**
     * 比较两个日期，忽略时间，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateIgnoreTime(Date date1, Date date2) {
        date1 = ignoreTime(date1);
        date2 = ignoreTime(date2);
        return compareDateTime(date1, date2);
    }

    /**
     * 比较两个时间，忽略分，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateTimeIgnoreMinute(Date date1, Date date2) {
        date1 = ignoreMinute(date1);
        date2 = ignoreMinute(date2);
        return compareDateTime(date1, date2);
    }

    /**
     * 比较两个时间，忽略秒，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateTimeIgnoreSecond(Date date1, Date date2) {
        date1 = ignoreSecond(date1);
        date2 = ignoreSecond(date2);
        return compareDateTime(date1, date2);
    }

    /**
     * 比较两个时间，忽略毫秒，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateTimeIgnoreMillisecond(Date date1, Date date2) {
        date1 = ignoreMillisecond(date1);
        date2 = ignoreMillisecond(date2);
        return compareDateTime(date1, date2);
    }

    /**
     * 比较两个时间，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateTime(Date date1, Date date2) {
        if (date1.after(date2))
            return 1;
        if (date1.before(date2))
            return -1;
        return 0;
    }

    /**
     * 忽略时间，把时、分、秒、毫秒都变为0
     *
     * @param date
     * @return
     */
    public static Date ignoreTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 方法用途和描述: 忽略分，分、秒、毫秒都变为0
     *
     * @param date
     * @return
     */
    public static Date ignoreMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 方法用途和描述: 忽略秒，秒、毫秒都变为0
     *
     * @param date
     * @return
     */
    public static Date ignoreSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 方法用途和描述: 忽略毫秒，毫秒变为0
     *
     * @param date
     * @return
     */
    public static Date ignoreMillisecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 方法用途和描述: 获取本周的开始时间
     *
     * @param weekFirstDateForm 一周的第一天由星期几开始，如果为空则取默认的星期一开始
     * @return
     */
    public static Date getCurrentWeekBeginDate(Integer weekFirstDateForm) {
        Calendar calendar = Calendar.getInstance();
        boolean isSunday = isSunday(calendar);
        if (weekFirstDateForm == null || weekFirstDateForm < Calendar.SUNDAY || weekFirstDateForm > Calendar.SATURDAY)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 默认设置从周一开始
        else
            calendar.set(Calendar.DAY_OF_WEEK, weekFirstDateForm);
        calendar.setTime(ignoreTime(calendar.getTime()));
        if (isSunday)
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - Calendar.DAY_OF_WEEK);
        return calendar.getTime();
    }

    /**
     * 方法用途和描述: 获取本周的结束时间
     *
     * @param weekFirstDateForm 一周的第一天由星期几开始，如果为空则取默认的星期一开始
     * @return
     */
    public static Date getCurrentWeekEndDate(Integer weekFirstDateForm) {
        Calendar calendar = Calendar.getInstance();
        boolean isSunday = isSunday(calendar);
        if (weekFirstDateForm == null || weekFirstDateForm < Calendar.SUNDAY || weekFirstDateForm > Calendar.SATURDAY)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 设置从周一开始
        else
            calendar.set(Calendar.DAY_OF_WEEK, weekFirstDateForm);
        calendar.setTime(ignoreTime(calendar.getTime()));
        if (!isSunday)
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 方法用途和描述: 是否是星期天
     *
     * @param date
     * @return
     */
    public static boolean isSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * @param : @param str
     * @param : @param patten
     * @param : @return
     * @return : boolean
     * @throws :
     * @Description : 是否是时间字符串
     * @author : luok@ake.com.cn
     */
    public static boolean isDateStr(String dateStr, String pattern) {
        try {
            if (StringUtil.isEmpty(dateStr)) {
                return false;
            }
            parse(dateStr, pattern);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 方法用途和描述: 是否是星期天
     *
     * @param calendar
     * @return
     */
    public static boolean isSunday(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss:SSS类型的日期转换成yyyy-MM-dd类型的日期对象
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date convertLongDateTimeToDate(Date date) {
        try {
            return parse(format(date, defaultLongDateTimePatternStr), defaultDatePatternStr);
        } catch (ParseException e) {
            // will never happen
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 在指定的日期上添加一天
     *
     * @param date
     * @return
     */
    public static Date addOneDay(Date date) {
        return addDate(DatePart.dd, 1, date);
    }

    /**
     * 在指定的日期上添加指定天数
     *
     * @param date   需要加减年、月、日的日期对象
     * @param number 加减因子
     * @return
     */
    public static Date addDays(Date date, int number) {
        return addDate(DatePart.dd, number, date);
    }

    /**
     * 把日期对象加减年、月、日、小时、分钟后得到新的日期对象
     *
     * @param datepart 年、月、日、小时、分钟
     * @param number   加减因子
     * @param date     需要加减的日期对象
     * @return 新的日期对象
     */
    public static Date addDate(DatePart datepart, int number, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (DatePart.yy == datepart) {
            cal.add(Calendar.YEAR, number);
        } else if (DatePart.MM == datepart) {
            cal.add(Calendar.MONTH, number);
        } else if (DatePart.dd == datepart) {
            cal.add(Calendar.DATE, number);
        } else if (DatePart.HH == datepart) {
            cal.add(Calendar.HOUR, number);
        } else if (DatePart.mm == datepart) {
            cal.add(Calendar.MINUTE, number);
        } else if (DatePart.ss == datepart) {
            cal.add(Calendar.SECOND, number);
        } else if (DatePart.SSS == datepart) {
            cal.add(Calendar.MILLISECOND, number);
        } else {
            throw new IllegalArgumentException("addDate(DatePart, int, Date)方法参数非法: " + datepart);
        }
        return cal.getTime();
    }

    /**
     * @param dateStr 格式为yy-MM
     * @return
     * @throws ParseException
     */
    public static Date getEndDateOfMonth(String dateStr) throws ParseException {

        String dateString = dateStr + "-01";
        Date date = DateUtil.parse(dateString, DateUtil.defaultDatePatternStr);
        date = addMonth(date, 1);
        Date endDateOfDay = DateUtil.getEndOfDay(date);
        Date endDateOfMonth = DateUtil.addDays(endDateOfDay, -1);
        return endDateOfMonth;
    }

    public static Date getEndDateOfMonth(Date date) throws ParseException {

        date = addMonth(date, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        Date endDateOfDay = DateUtil.getEndOfDay(date);
        Date endDateOfMonth = DateUtil.addDays(endDateOfDay, -1);
        return endDateOfMonth;
    }

    public static Date getEndDateOfYear(Date date) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.add(Calendar.YEAR, 1);
        Date endDateOfDay = DateUtil.getEndOfDay(calendar.getTime());
        Date endDateOfMonth = DateUtil.addDays(endDateOfDay, -1);
        return endDateOfMonth;
    }

    public static Date addMonth(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + i);
        return calendar.getTime();

    }

    /**
     * @param dateStr 格式为yyyy-MM
     * @return
     * @throws ParseException
     */
    public static Date getBeginDateOfMonth(String dateStr) throws ParseException {

        String dateString = dateStr + "-01";
        Date date = DateUtil.parse(dateString, DateUtil.defaultDatePatternStr);
        Date beginDateOfMonth = DateUtil.getBeginOfDay(date);
        return beginDateOfMonth;
    }

    public static Date getBeginDateOfMonth(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        Date beginDateOfMonth = DateUtil.getBeginOfDay(date);
        return beginDateOfMonth;
    }

    public static Date getBeginDateOfYear(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, 0);
        Date beginDateOfMonth = DateUtil.getBeginOfDay(date);
        return beginDateOfMonth;
    }

    public static String getStringOfDate(Date date) {
        return getStringOfDate(date, null, null);
    }

    public static String getStringOfDate(Date date, String pattern, String mode) {
        Date modeDate = null;
        if (pattern == null)
            pattern = DateUtil.defaultDateTimePatternStr;
        if (mode == null)
            mode = DateUtil.defaultMode;
        if (mode.equals(DateUtil.defaultMode))
            modeDate = date;
        else if (mode.equals(DateUtil.beginOfDateMode))
            modeDate = DateUtil.getBeginOfDay(date);
        else if (mode.equals(DateUtil.endOfDateMode))
            modeDate = DateUtil.getEndOfDay(date);
        else
            throw new IllegalArgumentException("getStringOfDate(Date date,String pattern,String mode)方法参数非法: " + mode);

        Long dateTime = modeDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(dateTime));
    }

    /**
     * 次方法是传入任意的date,任意的格式,输出的是pattern模式的date
     */
    public static Date getDate(Date date, String pattern, String mode) throws ParseException {
        Date modeDate = null;
        if (pattern == null)
            pattern = DateUtil.defaultDateTimePatternStr;
        if (mode == null)
            mode = DateUtil.defaultMode;
        if (mode.equals(DateUtil.defaultMode))
            modeDate = date;
        else if (mode.equals(DateUtil.beginOfDateMode))
            modeDate = DateUtil.getBeginOfDay(date);
        else if (mode.equals(DateUtil.endOfDateMode))
            modeDate = DateUtil.getEndOfDay(date);
        else
            throw new IllegalArgumentException("getStringOfDate(Date date,String pattern,String mode)方法参数非法: " + mode);

        Long dateTime = modeDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = sdf.format(new Date(dateTime));
        return parse(dateString, pattern);
    }

    /**
     * 判断当前时间是否在指定时间闭区间之内
     *
     * @param validFrom
     * @param validTo
     * @return
     */
    public static boolean isDuring(Date specifiedDate, Date validFrom, Date validTo) {
        if (validFrom == null || validTo == null)
            return false;
        if (DateUtil.compareDateTime(specifiedDate, validFrom) >= 0 && DateUtil.compareDateTime(specifiedDate, validTo) <= 0)
            return true;
        return false;
    }

    /**
     * 判断某个指定时间是否在指定时间闭区间之内
     *
     * @param validFrom
     * @param validTo
     * @return
     */
    public static boolean isDuringIgnoreTime(Date specifiedDate, Date validFrom, Date validTo) {
        if (specifiedDate == null || validFrom == null || validTo == null)
            return false;
        return isDuring(ignoreTime(specifiedDate), ignoreTime(validFrom), ignoreTime(validTo));
    }

    /**
     * 判断当前时间是否在指定时间闭区间之内
     *
     * @param validFrom
     * @param validTo
     * @return
     */
    public static boolean isDuring(Date validFrom, Date validTo) {
        return isDuring(new Date(), validFrom, validTo);
    }

    /**
     * 判断当前时间是否在指定时间闭区间之内
     *
     * @param validFrom
     * @param validTo
     * @return
     */
    public static boolean isDuringIgnoreTime(Date validFrom, Date validTo) {
        return isDuringIgnoreTime(new Date(), validFrom, validTo);
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int getTwoDatesInterval(Date date1, Date date2) throws ParseException {
        Date begin = null, end = null;
        if (date1 instanceof java.sql.Date) {
            begin = DateUtil.convertLongDateTimeToDate(date1);
        } else {
            begin = DateUtil.ignoreTime(date1);
        }

        if (date2 instanceof java.sql.Date) {
            end = DateUtil.convertLongDateTimeToDate(date2);
        } else {
            end = DateUtil.ignoreTime(date2);
        }

        long days = (end.getTime() - begin.getTime()) / DateUtil.DAY_MILLISECOND;
        return (int) days;
    }

    /**
     * 获取两个日期相差的分钟数
     */
    public static int getTwoTimeInterval4minute(Date begin, Date end) throws ParseException {

        long minutes = (end.getTime() - begin.getTime()) / DateUtil.MINUTE_MILLISECOND;
        return Math.abs((int) minutes);
    }

    /**
     * 获取两个日期相差的分钟数
     */
    public static int getTwoTimeInterval4senconds(Date begin, Date end) throws ParseException {

        long senconds = (end.getTime() - begin.getTime()) / 1000;
        return Math.abs((int) senconds);
    }

    /**
     * 获取两个日期相差的天数
     */
    public static int getInterval(Date date1, Date date2) {
        try {
            return getTwoDatesInterval(date1, date2);
        } catch (ParseException e) {
            return 0;
        }
    }


    /**
     * 获取当前时间离当前月底还有多少天
     */
    public static int getDaysToEndOfMonth() {
        Calendar cal = Calendar.getInstance();
        int currentDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.get(Calendar.DAY_OF_MONTH) - currentDayOfMonth;
    }

    /**
     * 获取某个时间的月初时间点
     */
    public static Date getBomTimePoint(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * @Description : 获得两个时间点之前相差时间，以字符串形式返回
     */
    public static String getTwoTimesInterval(String date1String, String date2String) {
        String result = "";
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = parse(date1String, null);

            date2 = parse(date2String, null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long temp = date2.getTime() - date1.getTime();    //相差毫秒数
        long temp2 = temp / 1000;
        long day = temp2 / 60 / 60 / 24;                // 相差天数
        long hours = temp2 / 3600 % 24;                //相差小时数(除去天)
        long mins = temp2 / 60 % 60;                    //相差分钟数（除去天和小时）
        if (day > 0) {
            result = result + day + "天";
        }
        if (hours > 0) {
            result = result + " " + hours + "小时";
        }
        if (mins > 0) {
            result = result + " " + mins + "分钟";
        }
        return result;
    }

    /**
     * @Description : 获得两个时间点之前相差时间，以字符串形式返回
     */
    public static String getTwoTimesInterval(Date date1, Date date2) {
        String result = "";
        long temp = date2.getTime() - date1.getTime();    //相差毫秒数
        long temp2 = temp / 1000;
        long day = temp2 / 60 / 60 / 24;                // 相差天数
        long hours = temp2 / 3600 % 24;                //相差小时数(除去天)
        long mins = temp2 / 60 % 60;                    //相差分钟数（除去天和小时）
        if (day > 0) {
            result = result + day + "天";
        }
        if (hours > 0) {
            result = result + " " + hours + "小时";
        }
        if (mins > 0) {
            result = result + " " + mins + "分钟";
        }
        if (mins == 0) {
            result = result + " " + 1 + "分钟";
        }
        return result;
    }

    public static String getInterval(long seconds) {
        String result = "";
        long temp2 = seconds;
        long day = temp2 / 60 / 60 / 24;                // 相差天数
        long hours = temp2 / 3600 % 24;                //相差小时数(除去天)
        long mins = temp2 / 60 % 60;                    //相差分钟数（除去天和小时）
        if (day > 0) {
            result = result + day + "天";
        }
        if (hours > 0) {
            result = result + " " + hours + "小时";
        }
        if (mins > 0) {
            result = result + " " + mins + "分钟";
        }
        if (mins == 0) {
            result = result + " " + 1 + "分钟";
        }
        return result;
    }

    /**
     * @Description : 将字符串类型的时间戳转化为日期格式
     */
    public static String longStr2DateStr(String timestr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String DateStr = sdf.format(new Date(Long.parseLong(timestr)));
//		System.out.println(DateStr);
        return DateStr;
    }

    /**
     * 获取两个日期相差的分钟数，秒数进1，1s也算1分钟
     */
    public static int getTwoTimeIntervalMinutes(Date begin, Date end) throws ParseException {
        long mill = end.getTime() - begin.getTime();
        long minutes = mill % DateUtil.MINUTE_MILLISECOND == 0 ? mill / DateUtil.MINUTE_MILLISECOND : mill / DateUtil.MINUTE_MILLISECOND + 1;
        return Math.abs((int) minutes);
    }

    /**
     * 获取两个日期相差的秒数
     */
    public static int getTwoTimeIntervalSeconds(Date begin, Date end) {
        long inter = end.getTime() - begin.getTime();
        int seconds = (int) (inter / 1000);
        return seconds;
    }

    /**
     * 获取两个日期相差的月份数，保留两位小数 30天算一月
     */
    public static double getTwoTimeIntervalMonths(Date begin, Date end) throws ParseException {
        long mill = end.getTime() - begin.getTime();

        double months = mill / (DateUtil.DAY_MILLISECOND * 30.0);

        return Math.abs(months);
    }


    /**
     * 把字符串格式化成日期类型   为"yyyy-MM-dd HH:mm:ss"格式
     */
    public static Date parse(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.equals("")) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimePatternStr);
        Date date = simpleDateFormat.parse(dateStr);
        return date;
    }

    /**
     * 获取两个日期相差的分钟数，秒数进1，1s也算1分钟(后面时间-前面时间，返回值会为负数)
     */
    public static int getTwoTimeIntervalMinutesNew(Date begin, Date end) throws ParseException {
        long mill = end.getTime() - begin.getTime();
        long minutes = mill % DateUtil.MINUTE_MILLISECOND == 0 ? mill / DateUtil.MINUTE_MILLISECOND : mill / DateUtil.MINUTE_MILLISECOND + 1;
        int t = (int) Math.ceil((double) minutes);
        return t;
    }

    /**
     * 获取两个日期相差的天数，不足一天的按一天算(后面时间-前面时间，为负数就返回0)
     */
    public static String getTwoTimeIntervalDay(Date begin, Date end) {
        try {
            long mill = end.getTime() - begin.getTime();
            if (mill < 0)
                return "0天";
            long day = mill % DateUtil.DAY_MILLISECOND == 0 ? mill / DateUtil.DAY_MILLISECOND : mill / DateUtil.DAY_MILLISECOND + 1;
            int t = (int) Math.ceil((double) day);
            return "" + t + "天";
        } catch (Exception e) {
            e.printStackTrace();
            return "--";
        }
    }

    public static Date getParamDaysBeforeCurrentDateTime(int retainDays) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -retainDays);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String getCurBeginAndEndDate() {
        Date nowDate = new Date(System.currentTimeMillis());
        String startTime = convertDate2Str(getBeginOfDay(nowDate));
        String endTime = convertDate2Str(getEndOfDay(nowDate));
        return startTime + " - " + endTime;
    }

    public static List<Date> getDateListBetweenTwoDateWithBorder(Date startDate, Date endDate) {
        List<Date> list = new ArrayList<Date>();

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        endCal.set(Calendar.HOUR_OF_DAY, 0);
        endCal.set(Calendar.MINUTE, 0);
        endCal.set(Calendar.SECOND, 0);
        endCal.set(Calendar.MILLISECOND, 0);

        list.add(startCal.getTime());
        Calendar temp = startCal;
        while (temp.compareTo(endCal) < 0) {
            temp.add(Calendar.DATE, 1);
            list.add(temp.getTime());
        }

        return list;
    }


    //计算两个时间差,返回月份
    public static Double chargeTwoDate(Date d1, Date d2) {
        Double monthVal = 0d;

        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        //获取开始时间的年月日
        int bYear = c1.get(Calendar.YEAR);
        int bMonth = c1.get(Calendar.MONTH) + 1;
        int bDay = c1.get(Calendar.DAY_OF_MONTH);
        int bNum = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
        //获取结束时间的年月日
        int eYear = c2.get(Calendar.YEAR);
        int eMonth = c2.get(Calendar.MONTH) + 1;
        int eDay = c2.get(Calendar.DAY_OF_MONTH);
        int eNum = c2.getActualMaximum(Calendar.DAY_OF_MONTH);

        int yearToMonth = Integer.valueOf((eYear - bYear) * 12);
        if (bYear == eYear && bMonth == eMonth && bDay == eDay) {
            monthVal = 0.03;
            return monthVal;
        }
        if (bDay == 1) {//起始日期是当月第一天
            if (eDay == eNum) {
                monthVal = Double.valueOf(eMonth - bMonth + 1 + yearToMonth);
                return monthVal;
            } else {
                monthVal = Integer.valueOf(eMonth - bMonth) + new BigDecimal(eDay).divide(new BigDecimal(eNum), 2, RoundingMode.HALF_UP).doubleValue() + yearToMonth;
                return monthVal;
            }
        }
        if (bDay == bNum) {
            if (eDay == eNum) {
                monthVal = Double.valueOf(eMonth - bMonth + yearToMonth);
                return monthVal;
            } else {
                monthVal = Integer.valueOf(eMonth - bMonth - 1) + new BigDecimal(eDay).divide(new BigDecimal(eNum), 2, RoundingMode.HALF_UP).doubleValue() + yearToMonth;
                return monthVal;
            }
        }
        if (bDay != bNum && bDay != 1) {
            if ((bDay - eDay) == 1) {
                monthVal = Double.valueOf(eMonth - bMonth + yearToMonth);
                return monthVal;
            }
            if (eDay > (bDay - 1)) {
                monthVal = Integer.valueOf(eMonth - bMonth) + new BigDecimal(eDay - (bDay - 1)).divide(new BigDecimal(eNum), 2, RoundingMode.HALF_UP).doubleValue() + yearToMonth;
                return monthVal;
            }
            if (eDay < (bDay - 1)) {
                Calendar c3 = Calendar.getInstance();
                c3.setTime(d2);
                c3.add(Calendar.MONTH, 1);
                int rNum = c3.getActualMaximum(Calendar.DAY_OF_MONTH);

                monthVal = Integer.valueOf(eMonth - bMonth - 1) + new BigDecimal(eDay).divide(new BigDecimal(eNum), 2, RoundingMode.HALF_UP).doubleValue() + new BigDecimal(rNum - (bDay - 1)).divide(new BigDecimal(rNum), 2, RoundingMode.HALF_UP).doubleValue() + yearToMonth;
                return monthVal;
            }
        }


        return monthVal;
    }

    /**
     * 判断当前日期是星期几
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(defaultDatePatternStr);
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 判断当前日期是星期几
     */
    public static int dayForWeek(Date pTime) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(pTime);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获得一段时间的所有日期
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {

        int code = DateUtil.compareDateTime(dEnd, dBegin);
        if (code == 0 || code == -1) {
            return null;
        }

        List<Date> lDate = new ArrayList<Date>();
        //lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {

            lDate.add(calBegin.getTime());
            calBegin.add(Calendar.DAY_OF_MONTH, 1);

        }

        lDate.add(dEnd);

        return lDate;


    }

    /***
     *
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(java.util.Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    public static String getCron(String repeat, String executeTime) {

        StringBuffer sb = new StringBuffer();
        String time[] = executeTime.split(":");

        sb.append(time[2] + " ");
        sb.append(time[1] + " ");
        sb.append(time[0] + " ");
        sb.append("? * ");
        sb.append(repeat);
        return sb.toString();
    }


    public static String getTimeForAddMinute(String hh, Integer minute) {
        Calendar calendar = Calendar.getInstance();
        String pattern = "HH:mm:ss";

        Date d = null;
        try {
            d = parse(hh, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(d);

        calendar.add(Calendar.MINUTE, minute);

        return format(calendar.getTime(), pattern);
    }


    public static Date getTimeForAddMinute(Date hh, Integer minute) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(hh);

        calendar.add(Calendar.MINUTE, minute);

        return calendar.getTime();
    }

    static Calendar calendar = Calendar.getInstance();

    public static String getYearOfDate(Date date) {

        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        return year.toString();
    }

    public static String getMonthOfDate(Date date) {

        calendar.setTime(date);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        return month.toString();
    }

    public static String getDayOfDate(Date date) {

        calendar.setTime(date);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        return day.toString();
    }

    /**
     * 获取指定周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置周
        cal.set(Calendar.WEEK_OF_YEAR, week);
        // 设置该周第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * 获取指定周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置周
        cal.set(Calendar.WEEK_OF_YEAR, week);
        // 设置该周第一天为星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 设置最后一天是星期日
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6); // Sunday
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }

    /**
     * 获取当年的第几周
     *
     * @param Date 字符串类型的日期
     * @return
     */
    public static Integer getWeekOfYear(String Date) {

        LocalDate localDate = LocalDate.parse(Date);
        // 从周一开始算，跨年的日期也算同一周(1~7)
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
        // 计算当前时间在当年的第几周
        int weekOfYear = localDate.get(weekFields.weekOfYear());

        return weekOfYear;
    }
}