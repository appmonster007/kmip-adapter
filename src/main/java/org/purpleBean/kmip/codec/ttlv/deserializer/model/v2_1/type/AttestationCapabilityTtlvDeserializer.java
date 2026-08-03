package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.AttestationCapability;

public class AttestationCapabilityTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttestationCapability,
        AttestationCapability.AttestationCapabilityBuilder> {

  public AttestationCapabilityTtlvDeserializer() {
    super(AttestationCapability.kmipTag, AttestationCapability.encodingType);
  }

  @Override
  protected AttestationCapability.AttestationCapabilityBuilder createBuilder() {
    return AttestationCapability.builder();
  }

  @Override
  protected void setValue(AttestationCapability.AttestationCapabilityBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected AttestationCapability build(
      AttestationCapability.AttestationCapabilityBuilder builder) {
    return builder.build();
  }
}