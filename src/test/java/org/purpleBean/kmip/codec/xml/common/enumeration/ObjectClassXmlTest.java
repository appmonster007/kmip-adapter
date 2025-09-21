package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectClass;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ObjectClass XML Serialization")
class ObjectClassXmlTest extends AbstractXmlSerializationSuite<ObjectClass> {
    @Override
    protected Class<ObjectClass> type() {
        return ObjectClass.class;
    }

    @Override
    protected ObjectClass createDefault() {
        return new ObjectClass(ObjectClass.Standard.USER);
    }

    @Override
    protected ObjectClass createVariant() {
        return new ObjectClass(ObjectClass.Standard.SYSTEM);
    }
}
