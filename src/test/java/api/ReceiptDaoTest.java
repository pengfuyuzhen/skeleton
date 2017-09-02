package api;

import dao.JooqConfig;
import dao.ReceiptDao;
import generated.tables.records.ReceiptsRecord;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class ReceiptDaoTest {

    @Test
    public void testInsert() {
        org.jooq.Configuration jooqConfig = JooqConfig.setupJooq();
        ReceiptDao receiptDao = new ReceiptDao(jooqConfig);
        receiptDao.insert("Starbucks", BigDecimal.valueOf(5));
        receiptDao.insert("Burger King", BigDecimal.valueOf(10));
        receiptDao.insert("Shake Shack", BigDecimal.valueOf(12));
        List<ReceiptsRecord> receipts = receiptDao.getAllReceipts();

        Assert.assertEquals(receipts.size(), 3);
        Assert.assertEquals(receipts.get(1).getMerchant(), "Burger King");
    }
}
