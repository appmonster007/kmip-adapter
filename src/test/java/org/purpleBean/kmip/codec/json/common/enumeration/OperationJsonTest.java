package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.Operation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("Operation JSON Serialization")
class OperationJsonTest extends AbstractJsonSerializationSuite<Operation> {
    @Override
    protected Class<Operation> type() {
        return Operation.class;
    }

    @Override
    protected Operation createDefault() {
        return new Operation(Operation.Standard.CREATE);
    }

    @Override
    protected Operation createVariant() {
        return new Operation(Operation.Standard.CREATE_KEY_PAIR);
    }
}
