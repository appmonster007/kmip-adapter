package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.RotateLatest;

public class RotateLatestXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<RotateLatest, RotateLatest.RotateLatestBuilder> {

  public RotateLatestXmlDeserializer() {
    super(RotateLatest.kmipTag, RotateLatest.encodingType);
  }

  @Override
  protected RotateLatest.RotateLatestBuilder createBuilder() {
    return RotateLatest.builder();
  }

  @Override
  protected void setValue(RotateLatest.RotateLatestBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected RotateLatest build(RotateLatest.RotateLatestBuilder builder) {
    return builder.build();
  }
}