package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ValidationProfile;

/**
 * TTLV deserializer for {@link ValidationProfile}.
 */
public class ValidationProfileTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationProfile,
        ValidationProfile.ValidationProfileBuilder> {

  /**
   * Constructs a new {@link ValidationProfileTtlvDeserializer}.
   */
  public ValidationProfileTtlvDeserializer() {
    super(ValidationProfile.kmipTag, ValidationProfile.encodingType);
  }

  @Override
  protected ValidationProfile.ValidationProfileBuilder createBuilder() {
    return ValidationProfile.builder();
  }

  @Override
  protected void setValue(ValidationProfile.ValidationProfileBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected ValidationProfile build(ValidationProfile.ValidationProfileBuilder builder) {
    return builder.build();
  }
}