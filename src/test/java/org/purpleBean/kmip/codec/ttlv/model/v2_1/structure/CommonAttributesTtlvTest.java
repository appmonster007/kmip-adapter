package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.structure.CommonAttributes;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.util.Collections;

@DisplayName("CommonAttributes Ttlv Serialization Tests")
class CommonAttributesTtlvTest extends AbstractTtlvSerializationTestSuite<CommonAttributes> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<CommonAttributes> type() {
        return CommonAttributes.class;
    }

    @Override
    public CommonAttributes createDefault() {
        return CommonAttributes.of(Collections.emptyList());
    }

    @Override
    public CommonAttributes createVariant() {
        return CommonAttributes.of(Collections.emptyList());
    }
}
