package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;

public class FixedFieldLengthJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<FixedFieldLength,
        FixedFieldLength.FixedFieldLengthBuilder> {

  public FixedFieldLengthJsonDeserializer() {
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
