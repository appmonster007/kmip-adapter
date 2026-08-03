package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.NotifyOpRequestPayload;

public class NotifyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<NotifyOpRequestPayload,
        NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder> {

  public NotifyOpRequestPayloadTtlvDeserializer() {
    super(NotifyOpRequestPayload.kmipTag, NotifyOpRequestPayload.encodingType);
  }

  @Override
  protected NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder createBuilder() {
    return NotifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected NotifyOpRequestPayload build(
      NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
