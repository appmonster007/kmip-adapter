package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RotateNameType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RotateNameType XML Serialization")
class RotateNameTypeXmlTest extends AbstractXmlSerializationTestSuite<RotateNameType> {
    @Override
    public Class<RotateNameType> type() {
        return RotateNameType.class;
    }

    @Override
    public RotateNameType createDefault() {
        return RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    }

    @Override
    public RotateNameType createVariant() {
        return RotateNameType.Standard.URI.inst();
    }
}
