package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.MaximumItems;
import org.purplebean.kmip.model.core.type.StorageStatusMask;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LocateOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.OffsetItems;

public class LocateOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LocateOpRequestPayload,
        LocateOpRequestPayload.LocateOpRequestPayloadBuilder> {

  public LocateOpRequestPayloadTtlvDeserializer() {
    super(LocateOpRequestPayload.kmipTag, LocateOpRequestPayload.encodingType);
  }

  @Override
  protected LocateOpRequestPayload.LocateOpRequestPayloadBuilder createBuilder() {
    return LocateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.MAXIMUM_ITEMS ->
          builder.maximumItems(mapper.readValue(p, MaximumItems.class));
      case KmipTag.Standard.OFFSET_ITEMS ->
          builder.offsetItems(mapper.readValue(p, OffsetItems.class));
      case KmipTag.Standard.STORAGE_STATUS_MASK ->
          builder.storageStatusMask(mapper.readValue(p, StorageStatusMask.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LocateOpRequestPayload build(
      LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
