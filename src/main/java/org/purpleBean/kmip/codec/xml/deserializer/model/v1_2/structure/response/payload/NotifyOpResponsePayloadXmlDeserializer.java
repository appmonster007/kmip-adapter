package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.NotifyOpResponsePayload;

public class NotifyOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<NotifyOpResponsePayload,
        NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder> {

  public NotifyOpResponsePayloadXmlDeserializer() {
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
