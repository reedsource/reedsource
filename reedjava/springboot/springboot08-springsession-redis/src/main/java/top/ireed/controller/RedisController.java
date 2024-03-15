/*
 * FileName: RedisController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.ireed.utils.InjectRedis;

/**
 * 功能简述:
 * 〈redis控制器  本处只展示基础案例〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@RestController
public class RedisController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private InjectRedis injectRedis;

	@GetMapping("/boot/redis/{key}/{value}")
	public String setRedis(@PathVariable String key, @PathVariable String value) {
		stringRedisTemplate.opsForValue().set(key, value);
		return "设置redis数据成功  key=" + key + "  value= " + value;
	}

	@GetMapping("/boot/redis/{key}")
	public String getRedis(@PathVariable String key) {
		return "取值redis数据成功  key=" + key + "  value= " + stringRedisTemplate.opsForValue().get(key);
	}

	@GetMapping("/boot/redisId")
	public String getRedisId() {
		return "基于redis的永久自增长id " + injectRedis.getRedisId();
	}
}
