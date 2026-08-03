package org.purplebean.kmip.codec.json.model.core.structure;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purplebean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyWrappingSpecification Json Serialization Tests")
class KeyWrappingSpecificationJsonTest
    extends AbstractJsonSerializationTestSuite<KeyWrappingSpecification> {

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