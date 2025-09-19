package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AlternativeNameType XML Serialization")
class AlternativeNameTypeXmlTest extends AbstractXmlSerializationSuite<AlternativeNameType> {
    @Override
    protected Class<AlternativeNameType> type() {
        return AlternativeNameType.class;
    }

    @Override
    protected AlternativeNameType createDefault() {
        return new AlternativeNameType(AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING);
    }

    @Override
    protected AlternativeNameType createVariant() {
        return new AlternativeNameType(AlternativeNameType.Standard.URI);
    }
}
