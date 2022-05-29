/*
 * FileName: ChannelMap
 * Author:   reedsource
 */
package top.ireed.netty06room;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;
import top.ireed.deal.DealLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能简述:
 * 〈公共存储当前连接对象〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
class ChannelMap {
	private ChannelMap() {
	}

	/**
	 * 线程安全的map, 保存当前全部的 Channel 对象连接
	 */
	private static Map<String, NettyModel> channelSumMap = new ConcurrentHashMap<>();

	/**
	 * 添加一个Channel
	 *
	 * @param id Channel id
	 * @param sc Channel实体
	 */
	static void addTimeServerChannel(String id, String type, Channel sc) {
		NettyModel nettyModel = new NettyModel();
		nettyModel.setId(id);
		nettyModel.setType(type);
		nettyModel.setC(sc);
		channelSumMap.put(id, nettyModel);
		DealLog.log("连接对象数量增加 当前为 : " , channelSumMap.size());
	}

	/**
	 * 获取所有Channel
	 *
	 * @return 所有Channel Map
	 */
	static Map<String, NettyModel> getAllChannels() {
		return channelSumMap;
	}

	/**
	 * 通过id获得一个Channel
	 *
	 * @param id Channel id
	 * @return Channel
	 */
	static NettyModel getTimeServerChannel(String id) {
		return channelSumMap.get(id);
	}

	/**
	 * 删除一个Channel
	 *
	 * @param id Channel id
	 */
	static void removeTimeServerChannel(String id) {
		channelSumMap.remove(id);
	}

	static void writeAndFlush(String msg) {
		for (NettyModel m : channelSumMap.values()) {
			//不同的服务类型,发送的数据格式不同
			if ("Socket".equals(m.getType())) {
				m.getC().writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
			} else if ("WebSocket".equals(m.getType())) {
				TextWebSocketFrame tws = new TextWebSocketFrame(m.getC().id() + " 信息" + msg);
				m.getC().writeAndFlush(tws);
			}
		}
	}
}

