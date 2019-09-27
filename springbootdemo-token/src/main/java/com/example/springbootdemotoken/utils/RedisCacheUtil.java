package com.example.springbootdemotoken.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class RedisCacheUtil {
    @Autowired
    private JedisCluster jed;

    private static JedisCluster jedisCluster;

    public static JedisCluster getInstance(){
        return jedisCluster;
    }

    @PostConstruct
    public void init(){
        this.jedisCluster = jed;
    }
    public static boolean exists(String prefix,String key){
        if(jedisCluster.exists(prefix+key)){
            return true;
        }else{
            return false;
        }
    }
    public static boolean exists(String key){
        if(jedisCluster.exists(key)){
            return true;
        }else{
            return false;
        }
    }
    /**
     *  取出 缓存 数据
     * @param key
     * @return
     */
    public static String  get(String prefix,String key) {
        String value = jedisCluster.get(prefix+key);
        return value;
    }
    public static String  get(String key) {
        String value = jedisCluster.get(key);
        return value;
    }
    /**
     * 存入缓存数据
     * @param key
     * @param value
     */
    public static  void set(String prefix,String key,String value) {
        jedisCluster.set(prefix+key, value);
    }
    public static  void set(String key,String value) {
        jedisCluster.set(key, value);
    }

    /**
     * 删除 key 存贮
     * @param key
     * @return
     */
    public static Long  del(String prefix,String key) {
        Long value = jedisCluster.del(prefix+key);
        return value;
    }
    public static Long  del(String key) {
        Long value = jedisCluster.del(key);
        return value;
    }

    /**
     * 关闭redis连接
     */
    private static void closeJedis(JedisCluster jedisCluster) {
        if (jedisCluster != null) {
            try {
                jedisCluster.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 设置 过期时间 单位秒
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public static  void setTimeSecond(String key,String value,int seconds ) {
        jedisCluster.setex(key, seconds, value);
    }
    /**
     * 设置 过期时间 单位毫秒
     * @param key
     * @param value
     * @param milliseconds
     * @return
     */
    public static  void setTimeMilliseconds(String key,String value,long milliseconds ) {
        jedisCluster.psetex(key, milliseconds, value);
    }
    /**
     * 设置 过期时间 以天为单位
     * @param key
     * @param value
     * @param day
     * @return
     **/
    public static  void setTimeDay(String key,String value,int day ) {
        jedisCluster.psetex(key, day*24*60*60, value);
    }
   /**
     * 设置 过期时间 以小时为单位
     * @param key
     * @param value
     * @param Hour
     * @return
     **/
    public static  void setTimeHour(String key,String value,int Hour ) {
        jedisCluster.psetex(key, Hour*60*60, value);
    }
    /**
     * 设置 过期时间 以分钟为单位
     * @param key
     * @param value
     * @param minute
     * @return
     **/
    public static  void setTimeMinute(String key,String value,int minute ) {
        jedisCluster.psetex(key, minute*60, value);
        String string = jedisCluster.get(key);
        System.out.println(string);

    }

    /**
     * 自增：计数
     * @param key 已保存的key值
     */
    public static void incr(String key){
        jedisCluster.incr(key);

    }
}
