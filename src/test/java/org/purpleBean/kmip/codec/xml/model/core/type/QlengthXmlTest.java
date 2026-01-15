package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Qlength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Qlength XML Serialization Tests")
class QlengthXmlTest extends AbstractXmlSerializationTestSuite<Qlength> {

    @Override
    protected Class<Qlength> type() {
        return Qlength.class;
    }

    @Override
    protected Qlength createDefault() {
        return Qlength.builder().value(128).build();
    }

    @Override
    protected Qlength createVariant() {
        return Qlength.builder().value(256).build();
    }
}