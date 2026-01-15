package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AlternativeNameValue;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.common.structure.AlternativeName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AlternativeName Ttlv Serialization Tests")
class AlternativeNameTtlvTest extends AbstractTtlvSerializationTestSuite<AlternativeName> {

    @Override
    protected Class<AlternativeName> type() {
        return AlternativeName.class;
    }

    @Override
    protected AlternativeName createDefault() {
        return AlternativeName.builder()
                .alternativeNameValue(AlternativeNameValue.of("some-name"))
                .alternativeNameType(AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst())
                .build();
    }
}