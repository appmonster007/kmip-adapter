package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Fresh;

public class FreshJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Fresh, Fresh.FreshBuilder> {

  public FreshJsonDeserializer() {
    super(Fresh.kmipTag, Fresh.encodingType);
  }

  @Override
  protected Fresh.FreshBuilder createBuilder() {
    return Fresh.builder();
  }

  @Override
  protected void setValue(Fresh.FreshBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected Fresh build(Fresh.FreshBuilder builder) {
    return builder.build();
  }
}
