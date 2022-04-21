/*
 * FileName: MdEntityDocument
 * Author:   reedsource
 */
package top.ireed.deal.markdown02;

import cn.hutool.core.io.file.FileNameUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * 功能简述:
 * 〈记录目录信息的实体〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2021-03-21 21:33
 * reedsource@189.cn
 */
public class MdEntityDocument {
	/*路径原数据*/
	private File mdPath;
	/*路径是文件夹*/
	private boolean isDirectory;
	/*处理完成的标题数据*/
	private String title;
	/*归属的子实体*/
	private ArrayList<MdEntityDocument> list;
	/*解析为无序列表的文本格式*/
	private String parse;

	public MdEntityDocument() {
	}

	public MdEntityDocument(File mdPath, boolean isDirectory) {
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
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<MdEntityDocument> getList() {
		if (list == null) {
			return new ArrayList<>();
		}

		return list;
	}

	public void setList(ArrayList<MdEntityDocument> list) {
		this.list = list;
	}

	public String getParse() {
		return parse;
	}

	public void setParse(String parse) {
		this.parse = parse;
	}

}
