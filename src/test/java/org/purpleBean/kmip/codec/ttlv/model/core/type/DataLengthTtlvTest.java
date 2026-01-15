package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DataLength TTLV Serialization Tests")
class DataLengthTtlvTest extends AbstractTtlvSerializationTestSuite<DataLength> {

    @Override
    protected Class<DataLength> type() {
        return DataLength.class;
    }

    @Override
    protected DataLength createDefault() {
        return DataLength.of(128);
    }

    @Override
    protected DataLength createVariant() {
        return DataLength.of(256);
    }
}
