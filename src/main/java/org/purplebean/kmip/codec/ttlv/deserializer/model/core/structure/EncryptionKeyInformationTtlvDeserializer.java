package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * TTLV deserializer for {@link EncryptionKeyInformation}.
 */
public class EncryptionKeyInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<EncryptionKeyInformation,
        EncryptionKeyInformation.EncryptionKeyInformationBuilder> {

  /**
   * Constructs a new {@link EncryptionKeyInformationTtlvDeserializer}.
   */
  public EncryptionKeyInformationTtlvDeserializer() {
    super(EncryptionKeyInformation.kmipTag, EncryptionKeyInformation.encodingType);
  }

  @Override
  protected EncryptionKeyInformation.EncryptionKeyInformationBuilder createBuilder() {
    return EncryptionKeyInformation.builder();
  }

  @Override
  protected void setValue(EncryptionKeyInformation.EncryptionKeyInformationBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected EncryptionKeyInformation build(
      EncryptionKeyInformation.EncryptionKeyInformationBuilder builder) {
    return builder.build();
  }
}