package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.RotateNameValue;

public class RotateNameValueJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RotateNameValue, RotateNameValue.RotateNameValueBuilder> {

  public RotateNameValueJsonDeserializer() {
    super(RotateNameValue.kmipTag, RotateNameValue.encodingType);
  }

  @Override
  protected RotateNameValue.RotateNameValueBuilder createBuilder() {
    return RotateNameValue.builder();
  }

  @Override
  protected void setValue(RotateNameValue.RotateNameValueBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected RotateNameValue build(RotateNameValue.RotateNameValueBuilder builder) {
    return builder.build();
  }
}