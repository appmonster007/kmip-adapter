package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AlternativeNameType XML Serialization")
class AlternativeNameTypeXmlTest extends AbstractXmlSerializationTestSuite<AlternativeNameType> {
    @Override
    public Class<AlternativeNameType> type() {
        return AlternativeNameType.class;
    }

    @Override
    public AlternativeNameType createDefault() {
        return AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    }

    @Override
    public AlternativeNameType createVariant() {
        return AlternativeNameType.Standard.URI.inst();
    }
}
