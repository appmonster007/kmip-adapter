package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.OpaqueDataValue;

/**
 * XML deserializer for {@link OpaqueDataValue}.
 */
public class OpaqueDataValueXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<OpaqueDataValue, OpaqueDataValue.OpaqueDataValueBuilder> {

  /**
   * Constructs a new {@link OpaqueDataValueXmlDeserializer}.
   */
  public OpaqueDataValueXmlDeserializer() {
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