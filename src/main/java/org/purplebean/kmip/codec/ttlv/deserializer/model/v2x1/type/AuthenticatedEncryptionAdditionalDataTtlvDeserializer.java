package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.AuthenticatedEncryptionAdditionalData;

/**
 * TTLV deserializer for {@link AuthenticatedEncryptionAdditionalData}.
 */
public class AuthenticatedEncryptionAdditionalDataTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AuthenticatedEncryptionAdditionalData,
        AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder> {

  /**
   * Constructs a new {@link AuthenticatedEncryptionAdditionalDataTtlvDeserializer}.
   */
  public AuthenticatedEncryptionAdditionalDataTtlvDeserializer() {
    super(AuthenticatedEncryptionAdditionalData.kmipTag,
        AuthenticatedEncryptionAdditionalData.encodingType);
  }

  @Override
  protected AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder createBuilder() {
    return AuthenticatedEncryptionAdditionalData.builder();
  }

  @Override
  protected void setValue(
      AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder builder,
      byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected AuthenticatedEncryptionAdditionalData build(
      AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder builder) {
    return builder.build();
  }
}