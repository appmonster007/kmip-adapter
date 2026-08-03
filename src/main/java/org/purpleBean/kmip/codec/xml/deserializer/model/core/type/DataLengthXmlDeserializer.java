package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DataLength;

public class DataLengthXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<DataLength, DataLength.DataLengthBuilder> {

  public DataLengthXmlDeserializer() {
    super(DataLength.kmipTag, DataLength.encodingType);
  }

  @Override
  protected DataLength.DataLengthBuilder createBuilder() {
    return DataLength.builder();
  }

  @Override
  protected void setValue(DataLength.DataLengthBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected DataLength build(DataLength.DataLengthBuilder builder) {
    return builder.build();
  }
}