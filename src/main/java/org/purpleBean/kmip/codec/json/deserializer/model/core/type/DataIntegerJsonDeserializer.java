package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DataInteger;

public class DataIntegerJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<DataInteger, DataInteger.DataIntegerBuilder> {

  public DataIntegerJsonDeserializer() {
    super(DataInteger.kmipTag, DataInteger.encodingType);
  }

  @Override
  protected DataInteger.DataIntegerBuilder createBuilder() {
    return DataInteger.builder();
  }

  @Override
  protected void setValue(DataInteger.DataIntegerBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected DataInteger build(DataInteger.DataIntegerBuilder builder) {
    return builder.build();
  }
}
