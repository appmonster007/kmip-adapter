package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.request.payload.GetConstraintsOpRequestPayload;

/**
 * TTLV deserializer for {@link GetConstraintsOpRequestPayload}.
 */
public class GetConstraintsOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GetConstraintsOpRequestPayload,
        GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link GetConstraintsOpRequestPayloadTtlvDeserializer}.
   */
  public GetConstraintsOpRequestPayloadTtlvDeserializer() {
    super(GetConstraintsOpRequestPayload.kmipTag, GetConstraintsOpRequestPayload.encodingType);
  }

  @Override
  protected GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder createBuilder() {
    return GetConstraintsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected GetConstraintsOpRequestPayload build(
      GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
