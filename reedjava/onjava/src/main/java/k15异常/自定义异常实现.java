/*
 * FileName: 自定义异常实现
 * Author:   reedsource
 */
package k15异常;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈自定义异常实现〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class 自定义异常实现 {
    @Test
    public void 自定义异常实现_Test() {
        自定义异常实体 c = new 自定义异常实体(101);
        DealLog.log("Depositing $500...");
        c.deposit(500.00);
        try {
            DealLog.log("\nWithdrawing $100...");
            c.withdraw(100.00);
            DealLog.log("\nWithdrawing $600...");
            c.withdraw(600.00);
        } catch (自定义异常Exception e) {
            DealLog.log("Sorry, but you are short $"
                    + e.getAmount());
            e.printStackTrace();
        }

        //Depositing $500...
        //
        //Withdrawing $100...
        //
        //Withdrawing $600...
        //Sorry, but you are short $200.0
        //自定义异常Exception
        //        at 自定义异常实体.withdraw(自定义异常实体.java:25)
        //        at 自定义异常实现.main(自定义异常实现.java:13)
    }
}
