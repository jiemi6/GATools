package com.minkey.util;

import com.minkey.dto.RateObj;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.text.DecimalFormat;

public class DiskUtils {

    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取硬盘总大小
     */
    public static RateObj driver(){
        // 当前文件系统类
        FileSystemView fsv = FileSystemView.getFileSystemView();
        // 列出所有 磁盘
        File[] fs = File.listRoots();
        long total = 0;
        long free = 0;
        // 显示磁盘卷标
        for (int i = 0; i < fs.length; i++) {
//            fsv.getSystemDisplayName(fs[i]);
            //"总大小" +
            total += fs[i].getTotalSpace();
            //"剩余"
            free += fs[i].getFreeSpace();
        }
        RateObj rateObj = RateObj.create8Free(Long.valueOf(total).doubleValue(),Long.valueOf(free).doubleValue());

        return rateObj;
    }


}
