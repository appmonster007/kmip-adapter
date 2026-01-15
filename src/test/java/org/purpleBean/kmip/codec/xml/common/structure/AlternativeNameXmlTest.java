package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AlternativeNameValue;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.common.structure.AlternativeName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AlternativeName Xml Serialization Tests")
class AlternativeNameXmlTest extends AbstractXmlSerializationTestSuite<AlternativeName> {

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