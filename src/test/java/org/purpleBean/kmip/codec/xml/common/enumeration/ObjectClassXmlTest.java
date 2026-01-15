package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectClass;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObjectClass XML Serialization")
class ObjectClassXmlTest extends AbstractXmlSerializationTestSuite<ObjectClass> {
    @Override
    protected Class<ObjectClass> type() {
        return ObjectClass.class;
    }

    @Override
    protected ObjectClass createDefault() {
        return ObjectClass.Standard.USER.inst();
    }

    @Override
    protected ObjectClass createVariant() {
        return ObjectClass.Standard.SYSTEM.inst();
    }
}
