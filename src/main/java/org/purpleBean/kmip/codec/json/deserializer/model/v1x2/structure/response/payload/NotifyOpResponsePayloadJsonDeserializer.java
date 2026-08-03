package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v1x2.structure.response.payload.NotifyOpResponsePayload;

public class NotifyOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<NotifyOpResponsePayload,
        NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder> {

  public NotifyOpResponsePayloadJsonDeserializer() {
    super(NotifyOpResponsePayload.kmipTag, NotifyOpResponsePayload.encodingType);
  }

  @Override
  protected NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder createBuilder() {
    return NotifyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
// No fields to deserialize
  }

  @Override
  protected NotifyOpResponsePayload build(
      NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
