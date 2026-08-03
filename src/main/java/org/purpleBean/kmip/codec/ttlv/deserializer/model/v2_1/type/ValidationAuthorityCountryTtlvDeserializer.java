package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityCountry;

public class ValidationAuthorityCountryTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationAuthorityCountry,
        ValidationAuthorityCountry.ValidationAuthorityCountryBuilder> {

  public ValidationAuthorityCountryTtlvDeserializer() {
    super(ValidationAuthorityCountry.kmipTag, ValidationAuthorityCountry.encodingType);
  }

  @Override
  protected ValidationAuthorityCountry.ValidationAuthorityCountryBuilder createBuilder() {
    return ValidationAuthorityCountry.builder();
  }

  @Override
  protected void setValue(ValidationAuthorityCountry.ValidationAuthorityCountryBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected ValidationAuthorityCountry build(
      ValidationAuthorityCountry.ValidationAuthorityCountryBuilder builder) {
    return builder.build();
  }
}