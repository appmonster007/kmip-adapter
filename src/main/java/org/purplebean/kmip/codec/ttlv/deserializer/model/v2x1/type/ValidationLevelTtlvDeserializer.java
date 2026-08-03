package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ValidationLevel;

public class ValidationLevelTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationLevel, ValidationLevel.ValidationLevelBuilder> {

  public ValidationLevelTtlvDeserializer() {
    super(ValidationLevel.kmipTag, ValidationLevel.encodingType);
  }

  @Override
  protected ValidationLevel.ValidationLevelBuilder createBuilder() {
    return ValidationLevel.builder();
  }

  @Override
  protected void setValue(ValidationLevel.ValidationLevelBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected ValidationLevel build(ValidationLevel.ValidationLevelBuilder builder) {
    return builder.build();
  }
}