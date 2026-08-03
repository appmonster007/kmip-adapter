package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.ReKeyOpRequestPayload;

public class ReKeyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ReKeyOpRequestPayload,
        ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder> {

  public ReKeyOpRequestPayloadTtlvDeserializer() {
    super(ReKeyOpRequestPayload.kmipTag, ReKeyOpRequestPayload.encodingType);
  }

  @Override
  protected ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder createBuilder() {
    return ReKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.OFFSET -> builder.offset(mapper.readValue(p, Offset.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReKeyOpRequestPayload build(
      ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}