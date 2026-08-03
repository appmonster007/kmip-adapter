package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.LastChangeDate;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ObtainLeaseOpResponsePayload;

public class ObtainLeaseOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ObtainLeaseOpResponsePayload,
        ObtainLeaseOpResponsePayload.ObtainLeaseOpResponsePayloadBuilder> {

  public ObtainLeaseOpResponsePayloadJsonDeserializer() {
    super(ObtainLeaseOpResponsePayload.kmipTag, ObtainLeaseOpResponsePayload.encodingType);
  }

  @Override
  protected ObtainLeaseOpResponsePayload.ObtainLeaseOpResponsePayloadBuilder createBuilder() {
    return ObtainLeaseOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ObtainLeaseOpResponsePayload.ObtainLeaseOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(ctxt.readValue(p, LeaseTime.class));
      case KmipTag.Standard.LAST_CHANGE_DATE ->
          builder.lastChangeDate(ctxt.readValue(p, LastChangeDate.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ObtainLeaseOpResponsePayload build(
      ObtainLeaseOpResponsePayload.ObtainLeaseOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
