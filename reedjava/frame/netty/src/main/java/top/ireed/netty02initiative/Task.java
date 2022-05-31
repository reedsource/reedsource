/*
 * FileName: Task
 * Author:   reedsource
 */
package top.ireed.netty02initiative;


import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import top.ireed.deal.DealLog;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能简述:
 * 〈定时任务推送〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
public class Task implements Runnable {

	private final int time;

	Task(int time) {
		this.time = time;
	}

	@Override
	public void run() {

		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			DealLog.log("推送任务异常", e);
			Thread.currentThread().interrupt();
		}
		to();
	}

	private void to() {
		Map<String, Channel> map = ChannelMap.getAllChannels();
		DealLog.log(time + "推送任务 map数量 " + map.size());
		if (map.size() > 0) {
			for (Map.Entry<String, Channel> stringChannelEntry : map.entrySet()) {
				Channel ch = map.get(stringChannelEntry.getKey());
				String msg = time + "秒定时推送任务 连接id" + stringChannelEntry.getKey();
				//写消息
				ch.write(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
				//冲刷消息
				ch.flush();
			}
		}
	}
}
