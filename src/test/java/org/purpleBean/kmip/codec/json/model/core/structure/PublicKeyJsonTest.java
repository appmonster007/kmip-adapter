package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PublicKey;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PublicKey Json Serialization Tests")
class PublicKeyJsonTest extends AbstractJsonSerializationTestSuite<PublicKey> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<PublicKey> type() {
        return PublicKey.class;
    }

    @Override
    public PublicKey createDefault() {
        return PublicKey.builder()
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
    }

    @Override
    public PublicKey createVariant() {
        return PublicKey.builder()
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
                        .build())
                .build();
    }
}