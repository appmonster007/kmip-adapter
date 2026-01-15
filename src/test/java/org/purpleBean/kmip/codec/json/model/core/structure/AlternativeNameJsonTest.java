package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;
import org.purpleBean.kmip.model.core.structure.AlternativeName;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AlternativeName Json Serialization Tests")
class AlternativeNameJsonTest extends AbstractJsonSerializationTestSuite<AlternativeName> {

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