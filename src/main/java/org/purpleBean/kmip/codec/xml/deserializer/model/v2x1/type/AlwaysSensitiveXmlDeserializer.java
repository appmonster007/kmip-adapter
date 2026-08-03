package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.AlwaysSensitive;

public class AlwaysSensitiveXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AlwaysSensitive, AlwaysSensitive.AlwaysSensitiveBuilder> {

  public AlwaysSensitiveXmlDeserializer() {
    super(AlwaysSensitive.kmipTag, AlwaysSensitive.encodingType);
  }

  @Override
  protected AlwaysSensitive.AlwaysSensitiveBuilder createBuilder() {
    return AlwaysSensitive.builder();
  }

  @Override
  protected void setValue(AlwaysSensitive.AlwaysSensitiveBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected AlwaysSensitive build(AlwaysSensitive.AlwaysSensitiveBuilder builder) {
    return builder.build();
  }
}