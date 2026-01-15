package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ObjectType JSON Serialization")
class ObjectTypeJsonTest extends AbstractJsonSerializationTestSuite<ObjectType> {
    @Override
    protected Class<ObjectType> type() {
        return ObjectType.class;
    }

    @Override
    protected ObjectType createDefault() {
        return ObjectType.Standard.CERTIFICATE.inst();
    }

    @Override
    protected ObjectType createVariant() {
        return ObjectType.Standard.SYMMETRIC_KEY.inst();
    }
}
