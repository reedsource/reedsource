package top.ireed.scene02.common.verify;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 案例验证接口
 * <p>
 * 逐条执行方案文档中的预期场景并返回结论,由平台方模块导入后暴露。
 */
@RestController
@RequestMapping("/scene02/api/verify")
public class VerifyController {

    private final VerifyRunner verifyRunner;

    public VerifyController(VerifyRunner verifyRunner) {
        this.verifyRunner = verifyRunner;
    }

    /**
     * 执行全部案例
     *
     * @return 案例结果与统计
     */
    @PostMapping("/run")
    public Map<String, Object> run() {
        List<CaseResult> results = verifyRunner.run();
        int passed = 0;
        for (CaseResult result : results) {
            if (result.isPassed()) {
                passed++;
            }
        }
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("total", results.size());
        summary.put("passed", passed);
        summary.put("failed", results.size() - passed);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("summary", summary);
        response.put("cases", results);
        return response;
    }
}
