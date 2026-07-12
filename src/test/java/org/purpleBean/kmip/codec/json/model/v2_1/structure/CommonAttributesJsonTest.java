package org.purpleBean.kmip.codec.json.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.structure.CommonAttributes;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.Collections;

@DisplayName("CommonAttributes Json Serialization Tests")
class CommonAttributesJsonTest extends AbstractJsonSerializationTestSuite<CommonAttributes> {

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
