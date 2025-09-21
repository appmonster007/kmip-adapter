package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectClass;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("ObjectClass JSON Serialization")
class ObjectClassJsonTest extends AbstractJsonSerializationSuite<ObjectClass> {
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
