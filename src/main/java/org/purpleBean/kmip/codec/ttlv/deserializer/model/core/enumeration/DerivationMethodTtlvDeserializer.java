package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.DerivationMethod;

public class DerivationMethodTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DerivationMethod,
        DerivationMethod.DerivationMethodBuilder> {

  public DerivationMethodTtlvDeserializer() {
    super(DerivationMethod.kmipTag, DerivationMethod.encodingType);
  }

  @Override
  protected DerivationMethod.DerivationMethodBuilder createBuilder() {
    return DerivationMethod.builder();
  }

  @Override
  protected void setValue(DerivationMethod.DerivationMethodBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(DerivationMethod.fromValue(value));
  }

  @Override
  protected DerivationMethod build(DerivationMethod.DerivationMethodBuilder builder) {
    return builder.build();
  }
}
