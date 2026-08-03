package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.DataInteger;

/**
 * XML deserializer for {@link DataInteger}.
 */
public class DataIntegerXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<DataInteger, DataInteger.DataIntegerBuilder> {

  /**
   * Constructs a new {@link DataIntegerXmlDeserializer}.
   */
  public DataIntegerXmlDeserializer() {
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
