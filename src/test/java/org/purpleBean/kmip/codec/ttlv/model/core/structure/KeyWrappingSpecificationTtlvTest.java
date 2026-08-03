package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyWrappingSpecification Ttlv Serialization Tests")
class KeyWrappingSpecificationTtlvTest
    extends AbstractTtlvSerializationTestSuite<KeyWrappingSpecification> {

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
    return KeyWrappingSpecification
        .builder()
        .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
        .encryptionKeyInformation(EncryptionKeyInformation
            .builder()
            .uniqueIdentifier(UniqueIdentifier.of(UUID
                .randomUUID()
                .toString()))
            .build())
        .build();
  }

  @Override
  public KeyWrappingSpecification createVariant() {
    return KeyWrappingSpecification
        .builder()
        .wrappingMethod(WrappingMethod.Standard.MAC_SIGN.inst())
        .encryptionKeyInformation(EncryptionKeyInformation
            .builder()
            .uniqueIdentifier(UniqueIdentifier.of(UUID
                .randomUUID()
                .toString()))
            .build())
        .build();
  }
}