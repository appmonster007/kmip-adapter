package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("UniqueBatchItemID Domain Tests")
class UniqueBatchItemIDTest extends AbstractKmipDataTypeSuite<UniqueBatchItemID> {

    @Override
    protected Class<UniqueBatchItemID> type() {
        return UniqueBatchItemID.class;
    }

    @Override
    protected UniqueBatchItemID createDefault() {
        return UniqueBatchItemID.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}