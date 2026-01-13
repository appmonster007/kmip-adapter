package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.OperationPolicyName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OperationPolicyName JSON Serialization Tests")
class OperationPolicyNameJsonTest extends AbstractJsonSerializationTestSuite<OperationPolicyName> {

    @Override
    protected Class<OperationPolicyName> type() {
        return OperationPolicyName.class;
    }

    @Override
    protected OperationPolicyName createDefault() {
        return OperationPolicyName.builder().value("test").build();
    }

    @Override
    protected OperationPolicyName createVariant() {
        return OperationPolicyName.builder().value("test2").build();
    }
}
