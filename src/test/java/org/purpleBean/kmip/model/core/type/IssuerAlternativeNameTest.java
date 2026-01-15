package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("IssuerAlternativeName Domain Tests")
class IssuerAlternativeNameTest extends AbstractKmipDataTypeTestSuite<IssuerAlternativeName> {

    @Override
    protected Class<IssuerAlternativeName> type() {
        return IssuerAlternativeName.class;
    }

    @Override
    protected IssuerAlternativeName createDefault() {
        return IssuerAlternativeName.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}