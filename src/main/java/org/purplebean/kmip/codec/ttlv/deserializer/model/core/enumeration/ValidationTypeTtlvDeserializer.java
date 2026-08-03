package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ValidationType;

/**
 * TTLV deserializer for {@link ValidationType}.
 */
public class ValidationTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationType, ValidationType.ValidationTypeBuilder> {

  /**
   * Constructs a new {@link ValidationTypeTtlvDeserializer}.
   */
  public ValidationTypeTtlvDeserializer() {
    super(ValidationType.kmipTag, ValidationType.encodingType);
  }

  @Override
  protected ValidationType.ValidationTypeBuilder createBuilder() {
    return ValidationType.builder();
  }

  @Override
  protected void setValue(ValidationType.ValidationTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ValidationType.fromValue(value));
  }

  @Override
  protected ValidationType build(ValidationType.ValidationTypeBuilder builder) {
    return builder.build();
  }
}
