package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.MaximumItems;
import org.purplebean.kmip.model.core.type.StorageStatusMask;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LocateOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.OffsetItems;

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
      case KmipTag.Standard.OFFSET_ITEMS ->
          builder.offsetItems(ctxt.readValue(p, OffsetItems.class));
      case KmipTag.Standard.STORAGE_STATUS_MASK ->
          builder.storageStatusMask(ctxt.readValue(p, StorageStatusMask.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LocateOpRequestPayload build(
      LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
