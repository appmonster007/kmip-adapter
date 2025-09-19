package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("NameType XML Serialization")
class NameTypeXmlTest extends AbstractXmlSerializationSuite<NameType> {
    @Override
    protected Class<NameType> type() {
        return NameType.class;
    }

    @Override
    protected NameType createDefault() {
        return new NameType(NameType.Standard.UNINTERPRETED_TEXT_STRING);
    }

    @Override
    protected NameType createVariant() {
        return new NameType(NameType.Standard.URI);
    }
}
