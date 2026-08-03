package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.response.payload.InteropOpResponsePayload;

public class InteropOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<InteropOpResponsePayload,
        InteropOpResponsePayload.InteropOpResponsePayloadBuilder> {

  public InteropOpResponsePayloadTtlvDeserializer() {
    super(InteropOpResponsePayload.kmipTag, InteropOpResponsePayload.encodingType);
  }

  @Override
  protected InteropOpResponsePayload.InteropOpResponsePayloadBuilder createBuilder() {
    return InteropOpResponsePayload.builder();
  }

  @Override
  protected void setValue(InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected InteropOpResponsePayload build(
      InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}