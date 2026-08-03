package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;

public class PaddingMethodJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PaddingMethod, PaddingMethod.PaddingMethodBuilder> {

  public PaddingMethodJsonDeserializer() {
    super(PaddingMethod.kmipTag, PaddingMethod.encodingType);
  }

  @Override
  protected PaddingMethod.PaddingMethodBuilder createBuilder() {
    return PaddingMethod.builder();
  }

  @Override
  protected void setValue(PaddingMethod.PaddingMethodBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(PaddingMethod.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected PaddingMethod build(PaddingMethod.PaddingMethodBuilder builder) {
    return builder.build();
  }
}
