package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.SecretDataType;

/**
 * JSON deserializer for {@link SecretDataType}.
 */
public class SecretDataTypeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SecretDataType, SecretDataType.SecretDataTypeBuilder> {

  /**
   * Constructs a new {@link SecretDataTypeJsonDeserializer}.
   */
  public SecretDataTypeJsonDeserializer() {
    super(SecretDataType.kmipTag, SecretDataType.encodingType);
  }

  @Override
  protected SecretDataType.SecretDataTypeBuilder createBuilder() {
    return SecretDataType.builder();
  }

  @Override
  protected void setValue(SecretDataType.SecretDataTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(SecretDataType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected SecretDataType build(SecretDataType.SecretDataTypeBuilder builder) {
    return builder.build();
  }
}
