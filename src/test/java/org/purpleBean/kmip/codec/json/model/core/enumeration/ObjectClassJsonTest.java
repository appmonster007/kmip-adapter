package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ObjectClass JSON Serialization")
class ObjectClassJsonTest extends AbstractJsonSerializationTestSuite<ObjectClass> {
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
