package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PublicKey;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PublicKey Xml Serialization Tests")
class PublicKeyXmlTest extends AbstractXmlSerializationTestSuite<PublicKey> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<PublicKey> type() {
        return PublicKey.class;
    }

    @Override
    protected PublicKey createDefault() {
        return PublicKey.builder()
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
    }

    @Override
    protected PublicKey createVariant() {
        return PublicKey.builder()
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
                        .build())
                .build();
    }
}