package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.EncryptOpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.AuthenticatedEncryptionTag;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;

/**
 * TTLV deserializer for {@link EncryptOpResponsePayload}.
 */
public class EncryptOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<EncryptOpResponsePayload,
        EncryptOpResponsePayload.EncryptOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link EncryptOpResponsePayloadTtlvDeserializer}.
   */
  public EncryptOpResponsePayloadTtlvDeserializer() {
    super(EncryptOpResponsePayload.kmipTag, EncryptOpResponsePayload.encodingType);
  }

  @Override
  protected EncryptOpResponsePayload.EncryptOpResponsePayloadBuilder createBuilder() {
    return EncryptOpResponsePayload.builder();
  }

  @Override
  protected void setValue(EncryptOpResponsePayload.EncryptOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      case KmipTag.Standard.IV_COUNTER_NONCE ->
          builder.ivCounterNonce(mapper.readValue(p, IVCounterNonce.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(mapper.readValue(p, CorrelationValue.class));
      case KmipTag.Standard.AUTHENTICATED_ENCRYPTION_TAG ->
          builder.authenticatedEncryptionTag(mapper.readValue(p, AuthenticatedEncryptionTag.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected EncryptOpResponsePayload build(
      EncryptOpResponsePayload.EncryptOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
