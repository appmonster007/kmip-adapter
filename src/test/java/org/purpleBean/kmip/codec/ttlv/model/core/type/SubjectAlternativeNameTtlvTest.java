package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SubjectAlternativeName TTLV Serialization Tests")
class SubjectAlternativeNameTtlvTest extends AbstractTtlvSerializationTestSuite<SubjectAlternativeName> {

    @Override
    public Class<SubjectAlternativeName> type() {
        return SubjectAlternativeName.class;
    }

    @Override
    public SubjectAlternativeName createDefault() {
        return SubjectAlternativeName.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public SubjectAlternativeName createVariant() {
        return SubjectAlternativeName.of(new byte[]{0x04, 0x05, 0x06});
    }
}