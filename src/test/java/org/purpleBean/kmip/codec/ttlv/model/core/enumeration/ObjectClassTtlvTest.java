package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectClass TTLV Serialization")
class ObjectClassTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectClass> {
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
