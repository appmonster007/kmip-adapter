package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ApplicationData;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ApplicationData XML Serialization Tests")
class ApplicationDataXmlTest extends AbstractXmlSerializationTestSuite<ApplicationData> {

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