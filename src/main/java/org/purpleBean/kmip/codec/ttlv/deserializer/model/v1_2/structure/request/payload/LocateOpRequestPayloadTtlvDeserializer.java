package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.MaximumItems;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.LocateOpRequestPayload;

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
      case KmipTag.Standard.STORAGE_STATUS_MASK ->
          builder.storageStatusMask(mapper.readValue(p, StorageStatusMask.class));
      case KmipTag.Standard.OBJECT_GROUP_MEMBER ->
          builder.objectGroupMember(mapper.readValue(p, ObjectGroupMember.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LocateOpRequestPayload build(
      LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
