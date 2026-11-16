package top.ireed.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.ireed.common.SceneResult;

/**
 * 场景全局异常处理
 * <p>
 * 统一将未捕获异常转换为 SceneResult,保证调用方拿到的响应结构一致。
 * 场景内新增业务异常时,在此补充对应的处理分支。
 */
@RestControllerAdvice
public class SceneExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(SceneExceptionHandler.class);

    /**
     * 兜底异常处理
     *
     * @param e 异常
     * @return 统一返回结构
     */
    @ExceptionHandler(Exception.class)
    public SceneResult<Void> handleException(Exception e) {
        log.error("场景未捕获异常", e);
        return SceneResult.fail(500, "系统异常:" + e.getMessage());
    }
}
