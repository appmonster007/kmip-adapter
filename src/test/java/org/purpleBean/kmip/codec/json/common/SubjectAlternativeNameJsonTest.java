package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SubjectAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SubjectAlternativeName JSON Serialization Tests")
class SubjectAlternativeNameJsonTest extends AbstractJsonSerializationTestSuite<SubjectAlternativeName> {

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