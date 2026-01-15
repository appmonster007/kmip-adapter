package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("FixedFieldLength XML Serialization Tests")
class FixedFieldLengthXmlTest extends AbstractXmlSerializationTestSuite<FixedFieldLength> {

    @Override
    protected Class<FixedFieldLength> type() {
        return FixedFieldLength.class;
    }

    @Override
    protected FixedFieldLength createDefault() {
        return FixedFieldLength.of(128);
    }

    @Override
    protected FixedFieldLength createVariant() {
        return FixedFieldLength.of(256);
    }
}