package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("RevocationReasonCode JSON Serialization")
class RevocationReasonCodeJsonTest extends AbstractJsonSerializationSuite<RevocationReasonCode> {
    @Override
    protected Class<RevocationReasonCode> type() {
        return RevocationReasonCode.class;
    }

    @Override
    protected RevocationReasonCode createDefault() {
        return new RevocationReasonCode(RevocationReasonCode.Standard.PLACEHOLDER_1);
    }

    @Override
    protected RevocationReasonCode createVariant() {
        return new RevocationReasonCode(RevocationReasonCode.Standard.PLACEHOLDER_2);
    }
}
