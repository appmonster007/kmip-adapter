package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.Sensitive;

public class SensitiveJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Sensitive, Sensitive.SensitiveBuilder> {

  public SensitiveJsonDeserializer() {
    super(Sensitive.kmipTag, Sensitive.encodingType);
  }

  @Override
  protected Sensitive.SensitiveBuilder createBuilder() {
    return Sensitive.builder();
  }

  @Override
  protected void setValue(Sensitive.SensitiveBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected Sensitive build(Sensitive.SensitiveBuilder builder) {
    return builder.build();
  }
}