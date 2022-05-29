/*
 * FileName: NettyModel
 * Author:   reedsource
 */
package top.ireed.netty06room;

import io.netty.channel.Channel;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
public class NettyModel {
	private String id;
	private String type;
	private Channel c;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Channel getC() {
		return c;
	}

	public void setC(Channel c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "NettyModel{" +
				"id='" + id + '\'' +
				", type='" + type + '\'' +
				", c=" + c +
				'}';
	}
}
