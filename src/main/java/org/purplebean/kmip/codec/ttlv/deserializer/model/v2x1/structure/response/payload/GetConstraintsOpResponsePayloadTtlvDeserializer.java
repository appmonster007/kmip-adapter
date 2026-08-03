package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.Constraints;
import org.purplebean.kmip.model.v2x1.structure.response.payload.GetConstraintsOpResponsePayload;

public class GetConstraintsOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GetConstraintsOpResponsePayload,
        GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder> {

  public GetConstraintsOpResponsePayloadTtlvDeserializer() {
    super(GetConstraintsOpResponsePayload.kmipTag, GetConstraintsOpResponsePayload.encodingType);
  }

  @Override
  protected GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder createBuilder() {
    return GetConstraintsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CONSTRAINTS ->
          builder.constraints(mapper.readValue(p, Constraints.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetConstraintsOpResponsePayload build(
      GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}