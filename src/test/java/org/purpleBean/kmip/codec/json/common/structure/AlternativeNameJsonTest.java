package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AlternativeNameValue;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.common.structure.AlternativeName;
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
                .alternativeNameType(new AlternativeNameType(AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING))
                .build();
    }
}