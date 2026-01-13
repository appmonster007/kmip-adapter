package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SerialNumber JSON Serialization Tests")
class SerialNumberJsonTest extends AbstractJsonSerializationTestSuite<SerialNumber> {

    @Override
    protected Class<SerialNumber> type() {
        return SerialNumber.class;
    }

    @Override
    protected SerialNumber createDefault() {
        return SerialNumber.builder().value("12345").build();
    }

    @Override
    protected SerialNumber createVariant() {
        return SerialNumber.builder().value("67890").build();
    }
}