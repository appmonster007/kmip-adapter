package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LocateOpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.LocatedItems;

public class LocateOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LocateOpResponsePayload,
        LocateOpResponsePayload.LocateOpResponsePayloadBuilder> {

  public LocateOpResponsePayloadTtlvDeserializer() {
    super(LocateOpResponsePayload.kmipTag, LocateOpResponsePayload.encodingType);
  }

  @Override
  protected LocateOpResponsePayload.LocateOpResponsePayloadBuilder createBuilder() {
    return LocateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(LocateOpResponsePayload.LocateOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LOCATED_ITEMS ->
          builder.locatedItems(mapper.readValue(p, LocatedItems.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LocateOpResponsePayload build(
      LocateOpResponsePayload.LocateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
