package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.Constraints;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetConstraintsOpRequestPayload;

/**
 * TTLV deserializer for {@link SetConstraintsOpRequestPayload}.
 */
public class SetConstraintsOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SetConstraintsOpRequestPayload,
        SetConstraintsOpRequestPayload.SetConstraintsOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link SetConstraintsOpRequestPayloadTtlvDeserializer}.
   */
  public SetConstraintsOpRequestPayloadTtlvDeserializer() {
    super(SetConstraintsOpRequestPayload.kmipTag, SetConstraintsOpRequestPayload.encodingType);
  }

  @Override
  protected SetConstraintsOpRequestPayload.SetConstraintsOpRequestPayloadBuilder createBuilder() {
    return SetConstraintsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      SetConstraintsOpRequestPayload.SetConstraintsOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CONSTRAINTS ->
          builder.constraints(mapper.readValue(p, Constraints.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetConstraintsOpRequestPayload build(
      SetConstraintsOpRequestPayload.SetConstraintsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}