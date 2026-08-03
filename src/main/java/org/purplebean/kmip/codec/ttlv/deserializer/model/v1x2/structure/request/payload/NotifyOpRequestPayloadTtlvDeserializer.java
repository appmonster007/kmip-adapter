package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.NotifyOpRequestPayload;

/**
 * TTLV deserializer for {@link NotifyOpRequestPayload}.
 */
public class NotifyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<NotifyOpRequestPayload,
        NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link NotifyOpRequestPayloadTtlvDeserializer}.
   */
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
