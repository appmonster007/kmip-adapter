package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.Operation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Operation JSON Serialization")
class OperationJsonTest extends AbstractJsonSerializationTestSuite<Operation> {
    @Override
    protected Class<Operation> type() {
        return Operation.class;
    }

    @Override
    protected Operation createDefault() {
        return Operation.Standard.CREATE.inst();
    }

    @Override
    protected Operation createVariant() {
        return Operation.Standard.CREATE_KEY_PAIR.inst();
    }
}
