package org.purpleBean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.MaximumItems;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.LocateOpRequestPayload;

public class LocateOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<LocateOpRequestPayload,
        LocateOpRequestPayload.LocateOpRequestPayloadBuilder> {

  public LocateOpRequestPayloadXmlDeserializer() {
    super(LocateOpRequestPayload.kmipTag, LocateOpRequestPayload.encodingType);
  }

  @Override
  protected LocateOpRequestPayload.LocateOpRequestPayloadBuilder createBuilder() {
    return LocateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.MAXIMUM_ITEMS ->
          builder.maximumItems(ctxt.readValue(p, MaximumItems.class));
      case KmipTag.Standard.STORAGE_STATUS_MASK ->
          builder.storageStatusMask(ctxt.readValue(p, StorageStatusMask.class));
      case KmipTag.Standard.OBJECT_GROUP_MEMBER ->
          builder.objectGroupMember(ctxt.readValue(p, ObjectGroupMember.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LocateOpRequestPayload build(
      LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
