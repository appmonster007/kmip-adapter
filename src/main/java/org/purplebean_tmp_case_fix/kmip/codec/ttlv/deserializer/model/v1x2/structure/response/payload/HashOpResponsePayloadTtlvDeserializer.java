package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.response.payload.HashOpResponsePayload;

public class HashOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<HashOpResponsePayload,
        HashOpResponsePayload.HashOpResponsePayloadBuilder> {

  public HashOpResponsePayloadTtlvDeserializer() {
    super(HashOpResponsePayload.kmipTag, HashOpResponsePayload.encodingType);
  }

  @Override
  protected HashOpResponsePayload.HashOpResponsePayloadBuilder createBuilder() {
    return HashOpResponsePayload.builder();
  }

  @Override
  protected void setValue(HashOpResponsePayload.HashOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected HashOpResponsePayload build(
      HashOpResponsePayload.HashOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
