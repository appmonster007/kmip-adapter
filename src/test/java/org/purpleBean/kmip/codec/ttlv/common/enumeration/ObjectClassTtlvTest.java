package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectClass;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectClass TTLV Serialization")
class ObjectClassTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectClass> {
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
