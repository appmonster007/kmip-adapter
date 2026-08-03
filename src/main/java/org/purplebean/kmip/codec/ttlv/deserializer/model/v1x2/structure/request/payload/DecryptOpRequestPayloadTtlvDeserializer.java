package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DecryptOpRequestPayload;

/**
 * TTLV deserializer for {@link DecryptOpRequestPayload}.
 */
public class DecryptOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DecryptOpRequestPayload,
        DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link DecryptOpRequestPayloadTtlvDeserializer}.
   */
  public DecryptOpRequestPayloadTtlvDeserializer() {
    super(DecryptOpRequestPayload.kmipTag, DecryptOpRequestPayload.encodingType);
  }

  @Override
  protected DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder createBuilder() {
    return DecryptOpRequestPayload.builder();
  }

  @Override
  protected void setValue(DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      case KmipTag.Standard.IV_COUNTER_NONCE ->
          builder.ivCounterNonce(mapper.readValue(p, IVCounterNonce.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DecryptOpRequestPayload build(
      DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}