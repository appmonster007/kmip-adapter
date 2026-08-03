package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ReplaceExisting;

public class ReplaceExistingJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ReplaceExisting, ReplaceExisting.ReplaceExistingBuilder> {

  public ReplaceExistingJsonDeserializer() {
    super(ReplaceExisting.kmipTag, ReplaceExisting.encodingType);
  }

  @Override
  protected ReplaceExisting.ReplaceExistingBuilder createBuilder() {
    return ReplaceExisting.builder();
  }

  @Override
  protected void setValue(ReplaceExisting.ReplaceExistingBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected ReplaceExisting build(ReplaceExisting.ReplaceExistingBuilder builder) {
    return builder.build();
  }
}