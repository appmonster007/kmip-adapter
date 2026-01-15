package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.structure.KeyWrappingData;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyWrappingData Ttlv Serialization Tests")
class KeyWrappingDataTtlvTest extends AbstractTtlvSerializationTestSuite<KeyWrappingData> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<KeyWrappingData> type() {
        return KeyWrappingData.class;
    }

    @Override
    protected KeyWrappingData createDefault() {
        return KeyWrappingData.builder()
                .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
                .build();
    }

    @Override
    protected KeyWrappingData createVariant() {
        return KeyWrappingData.builder()
                .wrappingMethod(WrappingMethod.Standard.MAC_SIGN.inst())
                .build();
    }
}