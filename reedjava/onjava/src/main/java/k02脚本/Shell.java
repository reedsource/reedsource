/*
 * FileName: Shell
 * Author:   reedsource
 */
package main.java.k02脚本;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java开发中常用的shell命令〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/6 22:56
 * reedsource@189.cn
 */
public class Shell {
    public static void main(String[] args) {
        DealLog.log("切换目录", "cd");
        DealLog.log("切换上级目录", "cd ..");
        DealLog.log("标记目录", "pushd");
        DealLog.log("跳转标记目录", "popd");
        DealLog.log("目录列表", "ls");
        DealLog.log("目录列表win", "dir");
        DealLog.log("进阶 列出F开头的java文件", "ls F*.java");

        DealLog.log("新建目录", "mkdir");
        DealLog.log("新建目录win", "md");
        DealLog.log("删除文件", "rm");
        DealLog.log("删除文件win", "del");
        DealLog.log("删除目录", "rm -r 目录");
        DealLog.log("删除目录win", "deltree 目录");
        DealLog.log("重复命令", "↑↓箭头");
        DealLog.log("重复命令Mac/Linux", "!! 重复执行上一条命令  !n重复执行第n条命令");

        DealLog.log("历史命令", "history");
        DealLog.log("历史命令win", "F7");

        DealLog.log("解压命令", "unzip");
    }

}
