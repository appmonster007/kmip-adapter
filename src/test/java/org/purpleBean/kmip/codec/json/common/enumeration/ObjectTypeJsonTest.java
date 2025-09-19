package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("ObjectType JSON Serialization")
class ObjectTypeJsonTest extends AbstractJsonSerializationSuite<ObjectType> {
    @Override
    protected Class<ObjectType> type() {
        return ObjectType.class;
    }

    @Override
    protected ObjectType createDefault() {
        return new ObjectType(ObjectType.Standard.CERTIFICATE);
    }

    @Override
    protected ObjectType createVariant() {
        return new ObjectType(ObjectType.Standard.SYMMETRIC_KEY);
    }
}
