package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.request.payload.HashOpRequestPayload;

/**
 * TTLV deserializer for {@link HashOpRequestPayload}.
 */
public class HashOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<HashOpRequestPayload,
        HashOpRequestPayload.HashOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link HashOpRequestPayloadTtlvDeserializer}.
   */
  public HashOpRequestPayloadTtlvDeserializer() {
    super(HashOpRequestPayload.kmipTag, HashOpRequestPayload.encodingType);
  }

  @Override
  protected HashOpRequestPayload.HashOpRequestPayloadBuilder createBuilder() {
    return HashOpRequestPayload.builder();
  }

  @Override
  protected void setValue(HashOpRequestPayload.HashOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected HashOpRequestPayload build(HashOpRequestPayload.HashOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}