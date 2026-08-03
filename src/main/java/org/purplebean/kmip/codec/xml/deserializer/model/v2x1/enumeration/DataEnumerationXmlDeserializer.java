package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.DataEnumeration;

/**
 * XML deserializer for {@link DataEnumeration}.
 */
public class DataEnumerationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DataEnumeration, DataEnumeration.DataEnumerationBuilder> {

  /**
   * Constructs a new {@link DataEnumerationXmlDeserializer}.
   */
  public DataEnumerationXmlDeserializer() {
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