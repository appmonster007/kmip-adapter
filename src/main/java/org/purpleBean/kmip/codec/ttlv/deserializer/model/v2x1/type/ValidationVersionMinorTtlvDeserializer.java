package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.ValidationVersionMinor;

public class ValidationVersionMinorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationVersionMinor,
        ValidationVersionMinor.ValidationVersionMinorBuilder> {

  public ValidationVersionMinorTtlvDeserializer() {
    super(ValidationVersionMinor.kmipTag, ValidationVersionMinor.encodingType);
  }

  @Override
  protected ValidationVersionMinor.ValidationVersionMinorBuilder createBuilder() {
    return ValidationVersionMinor.builder();
  }

  @Override
  protected void setValue(ValidationVersionMinor.ValidationVersionMinorBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected ValidationVersionMinor build(
      ValidationVersionMinor.ValidationVersionMinorBuilder builder) {
    return builder.build();
  }
}