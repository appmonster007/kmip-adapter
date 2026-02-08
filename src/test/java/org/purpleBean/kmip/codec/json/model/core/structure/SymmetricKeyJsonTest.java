package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SymmetricKey Json Serialization Tests")
class SymmetricKeyJsonTest extends AbstractJsonSerializationTestSuite<SymmetricKey> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<SymmetricKey> type() {
        return SymmetricKey.class;
    }

    @Override
    public SymmetricKey createDefault() {
        return SymmetricKey.builder()
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
    }

    @Override
    public SymmetricKey createVariant() {
        return SymmetricKey.builder()
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
                        .build())
                .build();
    }
}