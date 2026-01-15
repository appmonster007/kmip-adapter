package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectType TTLV Serialization")
class ObjectTypeTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectType> {
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
