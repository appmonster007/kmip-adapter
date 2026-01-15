package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SubjectAlternativeName XML Serialization Tests")
class SubjectAlternativeNameXmlTest extends AbstractXmlSerializationTestSuite<SubjectAlternativeName> {

    @Override
    protected Class<SubjectAlternativeName> type() {
        return SubjectAlternativeName.class;
    }

    @Override
    protected SubjectAlternativeName createDefault() {
        return SubjectAlternativeName.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected SubjectAlternativeName createVariant() {
        return SubjectAlternativeName.of(new byte[]{0x04, 0x05, 0x06});
    }
}