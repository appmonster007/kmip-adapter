package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.CreateKeyPairOpResponsePayload;

/**
 * TTLV deserializer for {@link CreateKeyPairOpResponsePayload}.
 */
public class CreateKeyPairOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateKeyPairOpResponsePayload,
        CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CreateKeyPairOpResponsePayloadTtlvDeserializer}.
   */
  public CreateKeyPairOpResponsePayloadTtlvDeserializer() {
    super(CreateKeyPairOpResponsePayload.kmipTag, CreateKeyPairOpResponsePayload.encodingType);
  }

  @Override
  protected CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder createBuilder() {
    return CreateKeyPairOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER ->
          builder.privateKeyUniqueIdentifier(mapper.readValue(p, PrivateKeyUniqueIdentifier.class));
      case KmipTag.Standard.PUBLIC_KEY_UNIQUE_IDENTIFIER ->
          builder.publicKeyUniqueIdentifier(mapper.readValue(p, PublicKeyUniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateKeyPairOpResponsePayload build(
      CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
