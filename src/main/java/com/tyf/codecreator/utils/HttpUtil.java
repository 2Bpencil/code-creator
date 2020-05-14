package com.tyf.codecreator.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Auther: tyf
 * @Date: 2019/7/11 16:37
 * @Description:
 */
public class HttpUtil {
    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断是否为ajax请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        if (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").equals(
                "XMLHttpRequest"))) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

        String ip_str = "192.168.10.34 127.0.0.1 3.3.3.3 105.70.11.55";

        // 为了让IP可以按字符串顺序比较，每一位需要0补充
        ip_str = ip_str.replaceAll("(\\d+)", "00$1");
        System.out.println(ip_str);

        // 然后每一个保留3位
        ip_str = ip_str.replaceAll("0*(\\d{3})", "$1");
        System.out.println(ip_str);

        // IP地址分割并排序
        String[] ips = ip_str.split(" +");
        Set<String> set = new TreeSet<String>();
        for (String ip : ips) {
            set.add(ip);
        }

        for (String ip : set) {
            System.out.println(ip.replaceAll("0*(\\d+)", "$1"));
        }


    }


}
