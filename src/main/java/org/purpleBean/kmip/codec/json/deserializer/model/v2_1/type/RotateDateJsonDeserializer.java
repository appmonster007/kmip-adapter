package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.RotateDate;

public class RotateDateJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<RotateDate, RotateDate.RotateDateBuilder> {

  public RotateDateJsonDeserializer() {
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