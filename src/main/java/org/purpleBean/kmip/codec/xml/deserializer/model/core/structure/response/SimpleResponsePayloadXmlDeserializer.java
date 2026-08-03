package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;

public class SimpleResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SimpleResponsePayload,
        SimpleResponsePayload.SimpleResponsePayloadBuilder> {

  public SimpleResponsePayloadXmlDeserializer() {
    super(SimpleResponsePayload.kmipTag, SimpleResponsePayload.encodingType);
  }

  @Override
  protected SimpleResponsePayload.SimpleResponsePayloadBuilder createBuilder() {
    return SimpleResponsePayload.builder();
  }

  @Override
  protected void setValue(SimpleResponsePayload.SimpleResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields to deserialize
  }

  @Override
  protected SimpleResponsePayload build(
      SimpleResponsePayload.SimpleResponsePayloadBuilder builder) {
    return builder.build();
  }
}
