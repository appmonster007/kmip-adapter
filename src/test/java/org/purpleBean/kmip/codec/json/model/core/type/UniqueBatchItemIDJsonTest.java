package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UniqueBatchItemID JSON Serialization Tests")
class UniqueBatchItemIDJsonTest extends AbstractJsonSerializationTestSuite<UniqueBatchItemID> {

    @Override
    protected Class<UniqueBatchItemID> type() {
        return UniqueBatchItemID.class;
    }

    @Override
    protected UniqueBatchItemID createDefault() {
        return UniqueBatchItemID.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected UniqueBatchItemID createVariant() {
        return UniqueBatchItemID.of(new byte[]{0x04, 0x05, 0x06});
    }
}