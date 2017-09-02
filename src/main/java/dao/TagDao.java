package dao;

import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import static com.google.common.base.Preconditions.checkState;
import java.util.List;

import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public int insert(String tagLabel, int receiptId) {
        TagsRecord tagsRecord = dsl.insertInto(TAGS, TAGS.LABEL, TAGS.RECEIPT_ID)
                .values(tagLabel, receiptId)
                .returning(TAGS.ID, TAGS.RECEIPT_ID, TAGS.LABEL)
                .fetchOne();
        checkState(tagsRecord != null && tagsRecord.getId() != null, "Insert failed");
        return tagsRecord.getId();
    }

    public List<TagsRecord> getAllTags() {
        return dsl.selectFrom(TAGS).fetch();
    }
}
