package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.FixedFieldLength;

public class FixedFieldLengthXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<FixedFieldLength,
        FixedFieldLength.FixedFieldLengthBuilder> {

  public FixedFieldLengthXmlDeserializer() {
    super(FixedFieldLength.kmipTag, FixedFieldLength.encodingType);
  }

  @Override
  protected FixedFieldLength.FixedFieldLengthBuilder createBuilder() {
    return FixedFieldLength.builder();
  }

  @Override
  protected void setValue(FixedFieldLength.FixedFieldLengthBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected FixedFieldLength build(FixedFieldLength.FixedFieldLengthBuilder builder) {
    return builder.build();
  }
}