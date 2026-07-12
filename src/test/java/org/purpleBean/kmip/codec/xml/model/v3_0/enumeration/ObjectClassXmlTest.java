package org.purpleBean.kmip.codec.xml.model.v3_0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.enumeration.ObjectClass;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObjectClass XML Serialization")
class ObjectClassXmlTest extends AbstractXmlSerializationTestSuite<ObjectClass> {
    @Override
    public Class<ObjectClass> type() {
        return ObjectClass.class;
    }

    @Override
    public ObjectClass createDefault() {
        return ObjectClass.Standard.USER.inst();
    }

    @Override
    public ObjectClass createVariant() {
        return ObjectClass.Standard.SYSTEM.inst();
    }
}
