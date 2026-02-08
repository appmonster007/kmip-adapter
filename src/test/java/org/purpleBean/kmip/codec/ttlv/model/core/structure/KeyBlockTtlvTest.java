package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyBlock Ttlv Serialization Tests")
class KeyBlockTtlvTest extends AbstractTtlvSerializationTestSuite<KeyBlock> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<KeyBlock> type() {
        return KeyBlock.class;
    }

    @Override
    public KeyBlock createDefault() {
        return KeyBlock.builder()
                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                .build();
    }

    @Override
    public KeyBlock createVariant() {
        return KeyBlock.builder()
                .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
                .build();
    }
}