package com.zdy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class IPUtils implements Serializable{

	 
	private static final long serialVersionUID = -8822836829251868086L;

	/**
     * 验证请求是否是本机发出
     * 
     * @param request true本机发出 false非本机发出
     * @return
     */
    public static boolean isRequestFromSelf(HttpServletRequest request) {
        if (getRemoteIpAddr(request).equals(getLocalIpAddr()))
            return true;
        else
            return false;
    }

    /**
     * 获取远程客户端IP地址
     * 
     * @param request
     * @return
     */
    public static String getRemoteIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");

        if (ip != null && !"".equals(ip)) {
            return ip;
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("Proxy-Client-IP");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("WL-Proxy-Client-IP");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_CLIENT_IP");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getRemoteAddr();

        if ("0:0:0:0:0:0:0:1".equals(ip.trim()))
            ip = "127.0.0.1";

        // 判断请求是否是本机发出,如果是本机发出,那么就获取本机地址
        // if ("127.0.0.1".equals(ip) || ip.equalsIgnoreCase("localhost"))
        // ip = getLocalIpAddr();

        return ip;
    }

    /**
     * 获取本机IP地址
     * 
     * @return
     */
    public static String getLocalIpAddr() {

        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        return ipAddrStr;
    }

    /**
     * @param authIps
     * @param ip
     * @return
     */
    public static boolean checkAuthIP(Long[][] authIps, String ip) {

        if (authIps == null) {
            return false;
        } else {
            String[] ipb = ip.split("\\.");
            Long iip = (Long.valueOf(ipb[0]) << 24) + (Long.valueOf(ipb[1]) << 16) + (Long.valueOf(ipb[2]) << 8)
                    + Long.valueOf(ipb[3]);

            for (int i = 0; i < authIps.length; i++) {
                if (iip >= authIps[i][0] && iip <= authIps[i][1]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ip地址集合转化为Long[][] 格式：127.0.0.1;192.168.1.*;192.168.0.1-192.168.0.254
     * 
     * @param ips
     * @return
     */
    public static Long[][] parseIps(String ips) {

        if (ips == null || "".equals(ips)) {
            return null;
        }

        String[] iparray = ips.split(";");

        Long[][] result = new Long[iparray.length][2];

        for (int i = 0; i < iparray.length; i++) {
            if (iparray[i] != null && !"".equals(iparray[i])) {
                String[] ipzone = iparray[i].split("-");
                Long iip;
                if (ipzone.length == 2) {
                    String[] ipb = ipzone[0].split("\\.");
                    String[] ipe = ipzone[1].split("\\.");
                    iip = (Long.valueOf(ipb[0]) << 24) + (Long.valueOf(ipb[1]) << 16) + (Long.valueOf(ipb[2]) << 8)
                            + Long.valueOf(ipb[3]);
                    result[i][0] = iip;
                    iip = (Long.valueOf(ipe[0]) << 24) + (Long.valueOf(ipe[1]) << 16) + (Long.valueOf(ipe[2]) << 8)
                            + Long.valueOf(ipe[3]);
                    result[i][1] = iip;
                } else {
                    String[] ipb = ipzone[0].replaceAll("\\*", "1").split("\\.");
                    String[] ipe = ipzone[0].replaceAll("\\*", "255").split("\\.");
                    iip = (Long.valueOf(ipb[0]) << 24) + (Long.valueOf(ipb[1]) << 16) + (Long.valueOf(ipb[2]) << 8)
                            + Long.valueOf(ipb[3]);
                    result[i][0] = iip;
                    iip = (Long.valueOf(ipe[0]) << 24) + (Long.valueOf(ipe[1]) << 16) + (Long.valueOf(ipe[2]) << 8)
                            + Long.valueOf(ipe[3]);
                    result[i][1] = iip;
                }
            }
        }

        return result;
    }

    /**
     * ip地址转化为long值
     * 
     * @param ip
     * @return
     */
    public static Long parseIp(String ip) {
        Long result = null;
        if (ip == null) {
            return result;
        }
        String[] iparray = ip.split("\\.");
        result = (Long.valueOf(iparray[0]) << 24) + (Long.valueOf(iparray[1]) << 16) + (Long.valueOf(iparray[2]) << 8)
                + Long.valueOf(iparray[3]);
        return result;
    }

    /**
     * 地址是否可达
     * 
     * @param ip
     * @return
     */
    public static boolean ping(String ip) {
        boolean result = false;
        BufferedReader br = null;

        try {
            String regex = "[^\n]*(icmp_seq)[^\n]*(ttl)[^\n]*(time)[^\n]*";
            String cmd = null;

            String osName = System.getProperties().getProperty("os.name");
            if (osName.startsWith("Linux")) {
                cmd = "ping -c 1 -w 3 " + ip;
            } else if (osName.startsWith("Mac")) {
                cmd = "ping -c 1 -t 3 " + ip;
            } else {
                return result;
            }

            Process process = Runtime.getRuntime().exec(cmd);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;

            List<String> tmpList = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                tmpList.add(line);
            }

            StringBuffer sbinfo = new StringBuffer();
            for (String info : tmpList) {
                sbinfo.append(info);
                sbinfo.append("\n");
            }

            Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(sbinfo.toString());
            while (m.find()) {
                try {
                    result = true;
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
        }

        return result;
    }

}
