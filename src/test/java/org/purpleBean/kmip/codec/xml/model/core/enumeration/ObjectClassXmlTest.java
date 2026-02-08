package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;
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
