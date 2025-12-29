package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SubjectAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("SubjectAlternativeName XML Serialization Tests")
class SubjectAlternativeNameXmlTest extends AbstractXmlSerializationSuite<SubjectAlternativeName> {

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