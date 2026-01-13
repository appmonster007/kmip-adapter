package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Offset;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Offset XML Serialization Tests")
class OffsetXmlTest extends AbstractXmlSerializationTestSuite<Offset> {

    @Override
    protected Class<Offset> type() {
        return Offset.class;
    }

    @Override
    protected Offset createDefault() {
        return Offset.builder().value(10).build();
    }

    @Override
    protected Offset createVariant() {
        return Offset.builder().value(20).build();
    }
}