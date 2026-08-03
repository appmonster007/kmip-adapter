package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.ValidationProfile;

public class ValidationProfileTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationProfile,
        ValidationProfile.ValidationProfileBuilder> {

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