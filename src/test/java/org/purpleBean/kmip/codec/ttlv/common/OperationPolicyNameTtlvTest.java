package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.OperationPolicyName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("OperationPolicyName TTLV Serialization Tests")
class OperationPolicyNameTtlvTest extends AbstractTtlvSerializationSuite<OperationPolicyName> {

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
