package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.DataByteString;

/**
 * JSON deserializer for {@link DataByteString}.
 */
public class DataByteStringJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DataByteString, DataByteString.DataByteStringBuilder> {

  /**
   * Constructs a new {@link DataByteStringJsonDeserializer}.
   */
  public DataByteStringJsonDeserializer() {
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
