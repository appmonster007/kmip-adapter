package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.DecryptOpResponsePayload;

/**
 * TTLV deserializer for {@link DecryptOpResponsePayload}.
 */
public class DecryptOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DecryptOpResponsePayload,
        DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link DecryptOpResponsePayloadTtlvDeserializer}.
   */
  public DecryptOpResponsePayloadTtlvDeserializer() {
    super(DecryptOpResponsePayload.kmipTag, DecryptOpResponsePayload.encodingType);
  }

  @Override
  protected DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder createBuilder() {
    return DecryptOpResponsePayload.builder();
  }

  @Override
  protected void setValue(DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DecryptOpResponsePayload build(
      DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
