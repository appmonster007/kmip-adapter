package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.RotateAutomatic;

public class RotateAutomaticXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RotateAutomatic, RotateAutomatic.RotateAutomaticBuilder> {

  public RotateAutomaticXmlDeserializer() {
    super(RotateAutomatic.kmipTag, RotateAutomatic.encodingType);
  }

  @Override
  protected RotateAutomatic.RotateAutomaticBuilder createBuilder() {
    return RotateAutomatic.builder();
  }

  @Override
  protected void setValue(RotateAutomatic.RotateAutomaticBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected RotateAutomatic build(RotateAutomatic.RotateAutomaticBuilder builder) {
    return builder.build();
  }
}