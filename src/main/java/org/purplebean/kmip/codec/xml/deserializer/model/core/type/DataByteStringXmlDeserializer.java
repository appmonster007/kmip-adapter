package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.DataByteString;

/**
 * XML deserializer for {@link DataByteString}.
 */
public class DataByteStringXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DataByteString, DataByteString.DataByteStringBuilder> {

  /**
   * Constructs a new {@link DataByteStringXmlDeserializer}.
   */
  public DataByteStringXmlDeserializer() {
    super(DataByteString.kmipTag, DataByteString.encodingType);
  }

  @Override
  protected DataByteString.DataByteStringBuilder createBuilder() {
    return DataByteString.builder();
  }

  @Override
  protected void setValue(DataByteString.DataByteStringBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected DataByteString build(DataByteString.DataByteStringBuilder builder) {
    return builder.build();
  }
}