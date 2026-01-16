package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyBlock Json Serialization Tests")
class KeyBlockJsonTest extends AbstractJsonSerializationTestSuite<KeyBlock> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<KeyBlock> type() {
        return KeyBlock.class;
    }

    @Override
    protected KeyBlock createDefault() {
        return KeyBlock.builder()
                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                .build();
    }

    @Override
    protected KeyBlock createVariant() {
        return KeyBlock.builder()
                .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
                .build();
    }
}