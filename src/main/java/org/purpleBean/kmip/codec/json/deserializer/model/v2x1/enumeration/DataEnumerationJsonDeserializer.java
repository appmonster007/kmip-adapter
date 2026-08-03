package org.purplebean.kmip.codec.json.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.DataEnumeration;

public class DataEnumerationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DataEnumeration, DataEnumeration.DataEnumerationBuilder> {

  public DataEnumerationJsonDeserializer() {
    super(DataEnumeration.kmipTag, DataEnumeration.encodingType);
  }

  @Override
  protected DataEnumeration.DataEnumerationBuilder createBuilder() {
    return DataEnumeration.builder();
  }

  @Override
  protected void setValue(DataEnumeration.DataEnumerationBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(DataEnumeration.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected DataEnumeration build(DataEnumeration.DataEnumerationBuilder builder) {
    return builder.build();
  }
}