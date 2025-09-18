package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.Operation;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("Operation TTLV Serialization")
class OperationTtlvTest extends AbstractTtlvSerializationSuite<Operation> {
    @Override
    protected Class<Operation> type() {
        return Operation.class;
    }

    @Override
    protected Operation createDefault() {
        return new Operation(Operation.Standard.PLACEHOLDER_1);
    }

    @Override
    protected Operation createVariant() {
        return new Operation(Operation.Standard.PLACEHOLDER_2);
    }
}
