package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.GetConstraintsOpRequestPayload;

public class GetConstraintsOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GetConstraintsOpRequestPayload,
        GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder> {

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
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetConstraintsOpRequestPayload build(
      GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}