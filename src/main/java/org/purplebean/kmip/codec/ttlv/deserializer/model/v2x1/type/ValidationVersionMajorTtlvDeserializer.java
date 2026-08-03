package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMajor;

/**
 * TTLV deserializer for {@link ValidationVersionMajor}.
 */
public class ValidationVersionMajorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationVersionMajor,
        ValidationVersionMajor.ValidationVersionMajorBuilder> {

  /**
   * Constructs a new {@link ValidationVersionMajorTtlvDeserializer}.
   */
  public ValidationVersionMajorTtlvDeserializer() {
    super(ValidationVersionMajor.kmipTag, ValidationVersionMajor.encodingType);
  }

  @Override
  protected ValidationVersionMajor.ValidationVersionMajorBuilder createBuilder() {
    return ValidationVersionMajor.builder();
  }

  @Override
  protected void setValue(ValidationVersionMajor.ValidationVersionMajorBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected ValidationVersionMajor build(
      ValidationVersionMajor.ValidationVersionMajorBuilder builder) {
    return builder.build();
  }
}