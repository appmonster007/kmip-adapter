package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ValidationAuthorityType;

/**
 * TTLV deserializer for {@link ValidationAuthorityType}.
 */
public class ValidationAuthorityTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationAuthorityType,
        ValidationAuthorityType.ValidationAuthorityTypeBuilder> {

  /**
   * Constructs a new {@link ValidationAuthorityTypeTtlvDeserializer}.
   */
  public ValidationAuthorityTypeTtlvDeserializer() {
    super(ValidationAuthorityType.kmipTag, ValidationAuthorityType.encodingType);
  }

  @Override
  protected ValidationAuthorityType.ValidationAuthorityTypeBuilder createBuilder() {
    return ValidationAuthorityType.builder();
  }

  @Override
  protected void setValue(ValidationAuthorityType.ValidationAuthorityTypeBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ValidationAuthorityType.fromValue(value));
  }

  @Override
  protected ValidationAuthorityType build(
      ValidationAuthorityType.ValidationAuthorityTypeBuilder builder) {
    return builder.build();
  }
}
