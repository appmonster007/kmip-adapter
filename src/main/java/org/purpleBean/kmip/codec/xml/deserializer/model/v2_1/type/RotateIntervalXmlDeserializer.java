package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.RotateInterval;

public class RotateIntervalXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RotateInterval, RotateInterval.RotateIntervalBuilder> {

  public RotateIntervalXmlDeserializer() {
    super(RotateInterval.kmipTag, RotateInterval.encodingType);
  }

  @Override
  protected RotateInterval.RotateIntervalBuilder createBuilder() {
    return RotateInterval.builder();
  }

  @Override
  protected void setValue(RotateInterval.RotateIntervalBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Long.class));
  }

  @Override
  protected RotateInterval build(RotateInterval.RotateIntervalBuilder builder) {
    return builder.build();
  }
}