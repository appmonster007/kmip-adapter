package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;

public class CriticalityIndicatorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CriticalityIndicator,
        CriticalityIndicator.CriticalityIndicatorBuilder> {

  public CriticalityIndicatorJsonDeserializer() {
    super(CriticalityIndicator.kmipTag, CriticalityIndicator.encodingType);
  }

  @Override
  protected CriticalityIndicator.CriticalityIndicatorBuilder createBuilder() {
    return CriticalityIndicator.builder();
  }

  @Override
  protected void setValue(CriticalityIndicator.CriticalityIndicatorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected CriticalityIndicator build(CriticalityIndicator.CriticalityIndicatorBuilder builder) {
    return builder.build();
  }
}
