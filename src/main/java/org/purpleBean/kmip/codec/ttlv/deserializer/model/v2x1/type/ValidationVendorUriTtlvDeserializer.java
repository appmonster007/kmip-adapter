package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ValidationVendorUri;

public class ValidationVendorUriTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationVendorUri,
        ValidationVendorUri.ValidationVendorUriBuilder> {

  public ValidationVendorUriTtlvDeserializer() {
    super(ValidationVendorUri.kmipTag, ValidationVendorUri.encodingType);
  }

  @Override
  protected ValidationVendorUri.ValidationVendorUriBuilder createBuilder() {
    return ValidationVendorUri.builder();
  }

  @Override
  protected void setValue(ValidationVendorUri.ValidationVendorUriBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected ValidationVendorUri build(ValidationVendorUri.ValidationVendorUriBuilder builder) {
    return builder.build();
  }
}