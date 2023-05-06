package main.java.j07IO系统.tools;

import junit.framework.TestCase;
import top.ireed.deal.DealLog;

import javax.sql.rowset.serial.SerialClob;
import java.sql.Clob;
import java.sql.SQLException;

public class IoToolsClobTest extends TestCase {

    public void testGetClobString() {
        try {
            String m = "{\"11111\":\"2222\"}";
            Clob clob = new SerialClob(m.toCharArray());
            DealLog.log(IoToolsClob.getClobString(clob));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}