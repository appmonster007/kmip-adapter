package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;
import org.purplebean.kmip.model.v2x1.structure.request.payload.InteropOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.InteropIdentifier;

public class InteropOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<InteropOpRequestPayload,
        InteropOpRequestPayload.InteropOpRequestPayloadBuilder> {

  public InteropOpRequestPayloadTtlvDeserializer() {
    super(InteropOpRequestPayload.kmipTag, InteropOpRequestPayload.encodingType);
  }

  @Override
  protected InteropOpRequestPayload.InteropOpRequestPayloadBuilder createBuilder() {
    return InteropOpRequestPayload.builder();
  }

  @Override
  protected void setValue(InteropOpRequestPayload.InteropOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.INTEROP_FUNCTION ->
          builder.interopFunction(mapper.readValue(p, InteropFunction.class));
      case KmipTag.Standard.INTEROP_IDENTIFIER ->
          builder.interopIdentifier(mapper.readValue(p, InteropIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected InteropOpRequestPayload build(
      InteropOpRequestPayload.InteropOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}