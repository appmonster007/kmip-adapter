package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;
import org.purpleBean.kmip.model.core.structure.AlternativeName;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AlternativeName Ttlv Serialization Tests")
class AlternativeNameTtlvTest extends AbstractTtlvSerializationTestSuite<AlternativeName> {

    @Override
    public Class<AlternativeName> type() {
        return AlternativeName.class;
    }

    @Override
    public AlternativeName createDefault() {
        return AlternativeName.builder()
                .alternativeNameValue(AlternativeNameValue.of("some-name"))
                .alternativeNameType(AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst())
                .build();
    }
}