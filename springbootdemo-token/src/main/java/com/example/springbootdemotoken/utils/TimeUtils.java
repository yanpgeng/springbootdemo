package com.example.springbootdemotoken.utils;

import cn.hutool.core.date.DateUnit;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Created by Leiyx on 2017/6/8.
 */
public class TimeUtils {

    private static String timePattern = "yyyy-MM-dd HH:mm:ss";

    private static LocalDate FIRSTDAYOFMONTH = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

    private static LocalDate LASTDAYOFMONTH = FIRSTDAYOFMONTH.with(TemporalAdjusters.lastDayOfMonth());

    private static LocalDate FIRSTDAYOFNEXTMONTH = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).plusDays(1);

    private static LocalDate LASTDAYOFNEXTMONTH = FIRSTDAYOFNEXTMONTH.with(TemporalAdjusters.lastDayOfMonth());


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getDateString() {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format;
    }

    /**
     * 日期格式转换
     *
     * @param str
     * @param format
     * @return
     */
    public static Date transferToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return
     * @throws
     * @Title: getFirstDayOfMonth
     * @Description: 当月的第一天
     * @author liuxiaolong
     * @date 2018/1/4
     * @modifier
     * @remark
     * @version V1.0
     */
    public static String getFirstDayOfMonth() {
        return FIRSTDAYOFMONTH.toString();
    }

    /**
     * @return
     * @throws
     * @Title: getFirstDayByNextMonth
     * @Description: 下月的第一天
     * @author liuxiaolong
     * @date 2018/1/4
     * @modifier
     * @remark
     * @version V1.0
     */
    public static String getFirstDayOfNextMonth() {
        return FIRSTDAYOFNEXTMONTH.toString();
    }

    /**
     * @return
     * @throws
     * @Title: getLastDayOfMonth
     * @Description: 当月的最后一天
     * @author liuxiaolong
     * @date 2018/1/4
     * @modifier
     * @remark
     * @version V1.0
     */
    public static String getLastDayOfMonth() {
        return LASTDAYOFMONTH.toString();
    }

    /**
     * @return
     * @throws
     * @Title: getLastDayOfNextMonth
     * @Description: 下月的最后一天
     * @author liuxiaolong
     * @date 2018/1/4
     * @modifier
     * @remark
     * @version V1.0
     */
    public static String getLastDayOfNextMonth() {
        return LASTDAYOFNEXTMONTH.toString();
    }

    /**
     * @param originalDate 指定日期
     * @return
     * @throws
     * @Title: getOriginalDateNextDay
     * @Description: 获取指定日期下一天
     * @author liuxiaolong
     * @date 2017/9/6
     * @modifier
     * @remark
     * @version V1.0
     */
    public static String getOriginalDateNextDay(Date originalDate) {

        LocalDateTime now = LocalDateTime.ofInstant(originalDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime of = now.plusDays(1);
        return of.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * @param originalDate 指定日期
     * @return
     * @throws
     * @Title: getOriginalDateNextDay
     * @Description: 获取指定日期下一天
     * @author liuxiaolong
     * @date 2017/9/6
     * @modifier
     * @remark
     * @version V1.0
     */
    public static String getOriginalDateNextDay(String originalDate) {
        Date orDate = transferToDate(originalDate, "yyyy-MM-dd");
        return getOriginalDateNextDay(orDate);
    }

    /**
     * @param originDate 指定日期 yyyy-MM-dd H:m:s
     * @return long
     * @Title: getDiffCurrentTime
     * @Description: 对比当前系统时间与指定日期的差值(秒)
     * @author xuyanjie
     * @date 2017/9/15
     * @modifier
     * @version V1.0
     */
    public static Long getDiffCurrentTime(String originDate) {

        LocalDateTime orgin = LocalDateTime.class.cast(LocalDateTime.parse(originDate.toString(), DateTimeFormatter.ofPattern(timePattern)));

        return Duration.between(orgin, LocalDateTime.now()).getSeconds();

    }

    /**
     * @param seconds 秒值
     * @return date
     * @Title: plusSeconds2Date
     * @Description: 系统时间顺延{秒值}后的date对象
     * @author xuyanjie
     * @date 2018/1/15
     * @version V1.0
     */
    public static Date plusSeconds2Date(Long seconds) {
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(seconds);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public class TimeInterval {

        private long time;
        private boolean isNano;

        public TimeInterval() {
            this(false);
        }

        public TimeInterval(boolean isNano) {
            this.isNano = isNano;
//            start();
        }

        /**
         * @return 开始计时并返回当前时间
         */
//        public long start() {
//            time = DateUtils.current(isNano);
//            return time;
//        }

        /**
         * @return 返回开始时间
         */
        public long startTime() {
            return time;
        }

        /**
         * @return 重新计时并返回从开始到当前的持续时间
         */
//        public long intervalRestart() {
//            long now = DateUtils.current(isNano);
//            long d = now - time;
//            time = now;
//            return d;
//        }

        /**
         * 重新开始计算时间（重置开始时间）
         *
         * @return this
         * @since 3.0.1
         */
//        public TimeInterval restart() {
//            time = DateUtils.current(isNano);
//            return this;
//        }

        //----------------------------------------------------------- Interval

        /**
         * 从开始到当前的间隔时间（毫秒数）<br>
         * 如果使用纳秒计时，返回纳秒差，否则返回毫秒差
         *
         * @return 从开始到当前的间隔时间（毫秒数）
         */
//        public long interval() {
//            return DateUtils.current(isNano) - time;
//        }

        /**
         * 从开始到当前的间隔时间（毫秒数）
         *
         * @return 从开始到当前的间隔时间（毫秒数）
         */
//        public long intervalMs() {
//            return isNano ? interval() / 1000000L : interval();
//        }

        /**
         * 从开始到当前的间隔秒数，取绝对值
         *
         * @return 从开始到当前的间隔秒数，取绝对值
         */
//        public long intervalSecond() {
//            return intervalMs() / DateUnit.SECOND.getMillis();
//        }

        /**
         * 从开始到当前的间隔分钟数，取绝对值
         *
         * @return 从开始到当前的间隔分钟数，取绝对值
         */
//        public long intervalMinute() {
//            return intervalMs() / DateUnit.MINUTE.getMillis();
//        }

        /**
         * 从开始到当前的间隔小时数，取绝对值
         *
         * @return 从开始到当前的间隔小时数，取绝对值
         */
//        public long intervalHour() {
//            return intervalMs() / DateUnit.HOUR.getMillis();
//        }

        /**
         * 从开始到当前的间隔天数，取绝对值
         *
         * @return 从开始到当前的间隔天数，取绝对值
         */
//        public long intervalDay() {
//            return intervalMs() / DateUnit.DAY.getMillis();
//        }

        /**
         * 从开始到当前的间隔周数，取绝对值
         *
         * @return 从开始到当前的间隔周数，取绝对值
         */
//        public long intervalWeek() {
//            return intervalMs() / DateUnit.WEEK.getMillis();
//        }

    }
}

