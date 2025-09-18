package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ObjectType XML Serialization")
class ObjectTypeXmlTest extends AbstractXmlSerializationSuite<ObjectType> {
    @Override
    protected Class<ObjectType> type() {
        return ObjectType.class;
    }

    @Override
    protected ObjectType createDefault() {
        return new ObjectType(ObjectType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ObjectType createVariant() {
        return new ObjectType(ObjectType.Standard.PLACEHOLDER_2);
    }
}
