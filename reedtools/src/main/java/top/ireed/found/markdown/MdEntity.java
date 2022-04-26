/*
 * FileName: MdEntityDocument
 * Author:   reedsource
 */
package top.ireed.found.markdown;

import cn.hutool.core.io.file.FileNameUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	/*处理完成的标题数据*/
	private String title;
	/**
	 * 当前路径与项目根目录层数的绝对值  默认创建文件为标题 为1 认为是根目录
	 */
	private int level;

	/*归属的子实体*/
	private List<MdEntity> list;
	/*解析为无序列表的文本格式*/
	private String unorderedListMsg;

	public MdEntity() {
	}

	/**
	 * @param mdPath      当前目录
	 * @param level       当前路径与项目根目录层数的绝对值  默认创建文件为标题 为1 认为是根目录
	 * @param isDirectory 是否是目录
	 */
	public MdEntity(File mdPath, int level, boolean isDirectory) {
		this.mdPath = mdPath;
		this.level = level;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSuffix() {
		return FileNameUtil.getSuffix(mdPath);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MdEntity> getList() {
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}

	public void setList(List<MdEntity> list) {
		this.list = list;
	}

	public String getUnorderedListMsg() {
		return unorderedListMsg;
	}

	public void setUnorderedListMsg(String unorderedListMsg) {
		this.unorderedListMsg = unorderedListMsg;
	}

}
