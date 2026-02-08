package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.UUID;

@DisplayName("KeyWrappingSpecification Xml Serialization Tests")
class KeyWrappingSpecificationXmlTest extends AbstractXmlSerializationTestSuite<KeyWrappingSpecification> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<KeyWrappingSpecification> type() {
        return KeyWrappingSpecification.class;
    }

    @Override
    public KeyWrappingSpecification createDefault() {
        return KeyWrappingSpecification.builder()
                .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
                .encryptionKeyInformation(EncryptionKeyInformation.builder()
                        .uniqueIdentifier(UniqueIdentifier.of(UUID.randomUUID().toString())).build())
                .build();
    }

    @Override
    public KeyWrappingSpecification createVariant() {
        return KeyWrappingSpecification.builder()
                .wrappingMethod(WrappingMethod.Standard.MAC_SIGN.inst())
                .encryptionKeyInformation(EncryptionKeyInformation.builder()
                        .uniqueIdentifier(UniqueIdentifier.of(UUID.randomUUID().toString())).build())
                .build();
    }
}