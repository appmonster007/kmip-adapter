package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.structure.response.payload.DelegatedLoginOpResponsePayload;

/**
 * JSON deserializer for {@link DelegatedLoginOpResponsePayload}.
 */
public class DelegatedLoginOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DelegatedLoginOpResponsePayload,
        DelegatedLoginOpResponsePayload.DelegatedLoginOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link DelegatedLoginOpResponsePayloadJsonDeserializer}.
   */
  public DelegatedLoginOpResponsePayloadJsonDeserializer() {
    super(DelegatedLoginOpResponsePayload.kmipTag, DelegatedLoginOpResponsePayload.encodingType);
  }

  @Override
  protected DelegatedLoginOpResponsePayload.DelegatedLoginOpResponsePayloadBuilder createBuilder() {
    return DelegatedLoginOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      DelegatedLoginOpResponsePayload.DelegatedLoginOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.TICKET -> builder.ticket(ctxt.readValue(p, Ticket.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DelegatedLoginOpResponsePayload build(
      DelegatedLoginOpResponsePayload.DelegatedLoginOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}