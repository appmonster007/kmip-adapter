package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.NeverExtractable;

public class NeverExtractableJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<NeverExtractable,
        NeverExtractable.NeverExtractableBuilder> {

  public NeverExtractableJsonDeserializer() {
    super(NeverExtractable.kmipTag, NeverExtractable.encodingType);
  }

  @Override
  protected NeverExtractable.NeverExtractableBuilder createBuilder() {
    return NeverExtractable.builder();
  }

  @Override
  protected void setValue(NeverExtractable.NeverExtractableBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected NeverExtractable build(NeverExtractable.NeverExtractableBuilder builder) {
    return builder.build();
  }
}