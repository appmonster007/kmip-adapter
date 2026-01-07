package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DataLength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("DataLength JSON Serialization Tests")
class DataLengthJsonTest extends AbstractJsonSerializationSuite<DataLength> {

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
