package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.LastChangeDate;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ObtainLeaseOpResponsePayload;

/**
 * JSON deserializer for {@link ObtainLeaseOpResponsePayload}.
 */
public class ObtainLeaseOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ObtainLeaseOpResponsePayload,
        ObtainLeaseOpResponsePayload.ObtainLeaseOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ObtainLeaseOpResponsePayloadJsonDeserializer}.
   */
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
