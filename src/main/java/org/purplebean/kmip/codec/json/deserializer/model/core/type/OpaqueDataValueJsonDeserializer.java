package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.OpaqueDataValue;

/**
 * JSON deserializer for {@link OpaqueDataValue}.
 */
public class OpaqueDataValueJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<OpaqueDataValue, OpaqueDataValue.OpaqueDataValueBuilder> {

  /**
   * Constructs a new {@link OpaqueDataValueJsonDeserializer}.
   */
  public OpaqueDataValueJsonDeserializer() {
    super(OpaqueDataValue.kmipTag, OpaqueDataValue.encodingType);
  }

  @Override
  protected OpaqueDataValue.OpaqueDataValueBuilder createBuilder() {
    return OpaqueDataValue.builder();
  }

  @Override
  protected void setValue(OpaqueDataValue.OpaqueDataValueBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected OpaqueDataValue build(OpaqueDataValue.OpaqueDataValueBuilder builder) {
    return builder.build();
  }
}
