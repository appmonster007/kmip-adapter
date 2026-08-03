package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetConstraintsOpResponsePayload;

/**
 * TTLV deserializer for {@link SetConstraintsOpResponsePayload}.
 */
public class SetConstraintsOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SetConstraintsOpResponsePayload,
        SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link SetConstraintsOpResponsePayloadTtlvDeserializer}.
   */
  public SetConstraintsOpResponsePayloadTtlvDeserializer() {
    super(SetConstraintsOpResponsePayload.kmipTag, SetConstraintsOpResponsePayload.encodingType);
  }

  @Override
  protected SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder createBuilder() {
    return SetConstraintsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected SetConstraintsOpResponsePayload build(
      SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}