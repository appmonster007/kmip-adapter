package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("SubjectAlternativeName Domain Tests")
class SubjectAlternativeNameTest extends AbstractKmipDataTypeSuite<SubjectAlternativeName> {

    @Override
    protected Class<SubjectAlternativeName> type() {
        return SubjectAlternativeName.class;
    }

    @Override
    protected SubjectAlternativeName createDefault() {
        return SubjectAlternativeName.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}