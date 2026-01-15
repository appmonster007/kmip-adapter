package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.Operation;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Operation TTLV Serialization")
class OperationTtlvTest extends AbstractTtlvSerializationTestSuite<Operation> {
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
