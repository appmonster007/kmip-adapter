package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DataLength JSON Serialization Tests")
class DataLengthJsonTest extends AbstractJsonSerializationTestSuite<DataLength> {

    @Override
    public Class<DataLength> type() {
        return DataLength.class;
    }

    @Override
    public DataLength createDefault() {
        return DataLength.of(128);
    }

    @Override
    public DataLength createVariant() {
        return DataLength.of(256);
    }
}
