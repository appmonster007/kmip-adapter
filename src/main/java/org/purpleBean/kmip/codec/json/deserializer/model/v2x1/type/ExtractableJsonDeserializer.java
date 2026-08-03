package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.Extractable;

public class ExtractableJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Extractable, Extractable.ExtractableBuilder> {

  public ExtractableJsonDeserializer() {
    super(Extractable.kmipTag, Extractable.encodingType);
  }

  @Override
  protected Extractable.ExtractableBuilder createBuilder() {
    return Extractable.builder();
  }

  @Override
  protected void setValue(Extractable.ExtractableBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected Extractable build(Extractable.ExtractableBuilder builder) {
    return builder.build();
  }
}