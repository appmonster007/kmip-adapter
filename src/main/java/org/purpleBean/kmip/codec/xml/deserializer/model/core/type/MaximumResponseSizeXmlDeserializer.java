package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;

public class MaximumResponseSizeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<MaximumResponseSize,
        MaximumResponseSize.MaximumResponseSizeBuilder> {

  public MaximumResponseSizeXmlDeserializer() {
    super(MaximumResponseSize.kmipTag, MaximumResponseSize.encodingType);
  }

  @Override
  protected MaximumResponseSize.MaximumResponseSizeBuilder createBuilder() {
    return MaximumResponseSize.builder();
  }

  @Override
  protected void setValue(MaximumResponseSize.MaximumResponseSizeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected MaximumResponseSize build(MaximumResponseSize.MaximumResponseSizeBuilder builder) {
    return builder.build();
  }
}