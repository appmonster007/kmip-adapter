package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AsynchronousCorrelationValue Domain Tests")
class AsynchronousCorrelationValueTest extends AbstractKmipDataTypeTestSuite<AsynchronousCorrelationValue> {

    @Override
    protected Class<AsynchronousCorrelationValue> type() {
        return AsynchronousCorrelationValue.class;
    }

    @Override
    protected AsynchronousCorrelationValue createDefault() {
        return AsynchronousCorrelationValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}