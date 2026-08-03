package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.DataLength;

/**
 * XML deserializer for {@link DataLength}.
 */
public class DataLengthXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<DataLength, DataLength.DataLengthBuilder> {

  /**
   * Constructs a new {@link DataLengthXmlDeserializer}.
   */
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