package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeactivationReasonCode,
        DeactivationReasonCode.DeactivationReasonCodeBuilder> {

  public DeactivationReasonCodeTtlvDeserializer() {
    super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType);
  }

  @Override
  protected DeactivationReasonCode.DeactivationReasonCodeBuilder createBuilder() {
    return DeactivationReasonCode.builder();
  }

  @Override
  protected void setValue(DeactivationReasonCode.DeactivationReasonCodeBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(DeactivationReasonCode.fromValue(value));
  }

  @Override
  protected DeactivationReasonCode build(
      DeactivationReasonCode.DeactivationReasonCodeBuilder builder) {
    return builder.build();
  }
}
