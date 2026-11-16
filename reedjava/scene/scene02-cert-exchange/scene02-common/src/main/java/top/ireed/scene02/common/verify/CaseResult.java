package top.ireed.scene02.common.verify;

import java.io.Serializable;

/**
 * 案例验证结果
 * <p>
 * 每个案例对应方案文档中的一个预期场景,记录结论与证据。
 */
public class CaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 案例编号
     */
    private String id;

    /**
     * 案例名称
     */
    private String name;

    /**
     * 对应方案文档位置
     */
    private String docRef;

    /**
     * 是否通过
     */
    private boolean passed;

    /**
     * 证据与说明
     */
    private String detail;

    /**
     * 耗时 毫秒
     */
    private long costMs;

    public CaseResult() {
    }

    public CaseResult(String id, String name, String docRef) {
        this.id = id;
        this.name = name;
        this.docRef = docRef;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocRef() {
        return docRef;
    }

    public void setDocRef(String docRef) {
        this.docRef = docRef;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getCostMs() {
        return costMs;
    }

    public void setCostMs(long costMs) {
        this.costMs = costMs;
    }
}
