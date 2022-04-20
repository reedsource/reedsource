/*
 * FileName: MdEntity
 * Author:   reedsource
 */
package top.ireed.deal.markdown01;

import cn.hutool.core.io.file.FileNameUtil;

import java.io.File;

/**
 * 功能简述:
 * 〈记录目录信息的实体〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2021-03-21 21:33
 * reedsource@189.cn
 */
public class MdEntity {
	/*路径原数据*/
	private File mdPath;
	/*路径是文件夹*/
	private boolean isDirectory;
	/*目录的等级*/
	private int level;
	/*提取出的只保留文件名的字符串*/
	private String name;
	/*文件后缀*/
	private String suffix;
	/*处理完成的标题数据*/
	private String title;
	/*处理完成的内容文本*/
	private String text;

	public MdEntity(File mdPath, boolean isDirectory) {
		this.mdPath = mdPath;
		this.isDirectory = isDirectory;
	}

	public void setMdPath(File mdPath) {
		this.mdPath = mdPath;
	}

	public void setDirectory(boolean directory) {
		isDirectory = directory;
	}

	public File getMdPath() {
		return mdPath;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	/**
	 * @param pathSum 初始时 目录层级别 本处专用
	 * @return 减去初始层级之后的值
	 */
	public int getLevel(int pathSum) {
		//通过\\获取数组 取数组的成员数量 判定层级
		//减去初始遍历的根目录的层级
		return mdPath.getPath().split("\\\\").length - pathSum + 1;
	}

	public String getName() {
		//获取文件名，不带扩展后缀
		return mdPath.getName();
	}

	public String getSuffix() {
		return FileNameUtil.getSuffix(mdPath);
		//截取路径的文件后缀
		//return mdPath.toString().substring(mdPath.toString().lastIndexOf("."));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "MdEntity{" +
				"mdPath=" + mdPath +
				", isDirectory=" + isDirectory +
				", level=" + getLevel(0) +
				", name='" + getName() + '\'' +
				", suffix='" + getSuffix() + '\'' +
				", title='" + title + '\'' +
				", text='" + text + '\'' +
				'}';
	}

}
