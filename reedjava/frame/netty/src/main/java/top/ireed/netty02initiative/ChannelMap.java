/*
 * FileName: ChannelMap
 * Author:   reedsource
 */
package top.ireed.netty02initiative;

import io.netty.channel.Channel;
import top.ireed.deal.DealLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能简述:
 * 〈存储当前连接对象〉
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
	 * 连接对象组
	 */
	private static Map<String, Channel> channelSumMap = new ConcurrentHashMap<>();

	static void addTimeServerChannel(String id, Channel sc) {
		channelSumMap.put(id, sc);
		DealLog.log("连接对象数量增加 当前为 :", channelSumMap.size());
	}

	static Map<String, Channel> getAllChannels() {
		return channelSumMap;
	}

	static Channel getTimeServerChannel(String id) {
		return channelSumMap.get(id);
	}

	static void removeTimeServerChannel(String id) {
		channelSumMap.remove(id);
	}


}
