package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ApplicationData;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ApplicationData TTLV Serialization Tests")
class ApplicationDataTtlvTest extends AbstractTtlvSerializationSuite<ApplicationData> {

    @Override
    protected Class<ApplicationData> type() {
        return ApplicationData.class;
    }

    @Override
    protected ApplicationData createDefault() {
        return ApplicationData.builder().value("test-data").build();
    }

    @Override
    protected ApplicationData createVariant() {
        return ApplicationData.builder().value("another-data").build();
    }
}