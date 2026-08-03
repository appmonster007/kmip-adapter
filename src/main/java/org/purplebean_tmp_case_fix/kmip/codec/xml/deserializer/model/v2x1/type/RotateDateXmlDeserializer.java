package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.RotateDate;

public class RotateDateXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<RotateDate, RotateDate.RotateDateBuilder> {

  public RotateDateXmlDeserializer() {
    super(RotateDate.kmipTag, RotateDate.encodingType);
  }

  @Override
  protected RotateDate.RotateDateBuilder createBuilder() {
    return RotateDate.builder();
  }

  @Override
  protected void setValue(RotateDate.RotateDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected RotateDate build(RotateDate.RotateDateBuilder builder) {
    return builder.build();
  }
}