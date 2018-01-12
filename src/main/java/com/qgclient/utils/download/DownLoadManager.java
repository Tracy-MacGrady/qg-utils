package com.qgclient.utils.download;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wh on 16/9/21.
 */
public class DownLoadManager {

    public static File getFileFromServer(String path, BaseDownloadProgressDialog pd, String referer) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty(
                    "Accept",
                    "image/gif, image/jpeg, image/pjpeg, image/pjpeg, " +
                            "application/x-shockwave-flash, application/xaml+xml, " +
                            "application/vnd.ms-xpsdocument, application/x-ms-xbap, " +
                            "application/x-ms-application, application/vnd.ms-excel, " +
                            "application/vnd.ms-powerpoint, application/msword, */*");
            conn.setRequestProperty("Accept-Language", "zh-CN");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Referer", referer);
            //设置浏览器类型和版本、操作系统，使用语言等信息
            conn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; Trident/4.0; " +
                            ".NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; " +
                            ".NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            //设置为长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            //获取到文件的大小
            pd.setMax(conn.getContentLength() / (1024 * 1024));
            pd.setCanceledOnTouchOutside(false);
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(), "updatas.apk");
            if (!file.exists()) {
            }
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total / (1024 * 1024));
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }
}
