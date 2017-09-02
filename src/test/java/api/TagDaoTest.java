package api;

import dao.JooqConfig;
import dao.ReceiptDao;
import dao.TagDao;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class TagDaoTest {

    @Test
    public void tagTest() {
        org.jooq.Configuration jooqConfig = JooqConfig.setupJooq();
        ReceiptDao receiptDao = new ReceiptDao(jooqConfig);
        TagDao tagDao = new TagDao(jooqConfig);
        int receiptId = receiptDao.insert("Burger King", BigDecimal.valueOf(10));
        tagDao.insert("Food", receiptId);

        Assert.assertEquals(tagDao.isReceiptTagged("Food", receiptId), true);
        Assert.assertEquals(tagDao.isReceiptTagged("Food", receiptId + 1), false);
    }

    @Test
    public void untagTest() {
        org.jooq.Configuration jooqConfig = JooqConfig.setupJooq();
        ReceiptDao receiptDao = new ReceiptDao(jooqConfig);
        TagDao tagDao = new TagDao(jooqConfig);
        int receiptId = receiptDao.insert("Starbucks", BigDecimal.valueOf(10));
        tagDao.insert("Coffee", receiptId);
        tagDao.insert("Coffee", receiptId);
        Assert.assertEquals(tagDao.isReceiptTagged("Food", receiptId), false);
    }
}
