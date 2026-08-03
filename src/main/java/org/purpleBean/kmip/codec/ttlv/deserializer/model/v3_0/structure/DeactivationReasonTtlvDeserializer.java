package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.model.v3_0.structure.DeactivationReason;
import org.purpleBean.kmip.model.v3_0.type.DeactivationMessage;

public class DeactivationReasonTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeactivationReason,
        DeactivationReason.DeactivationReasonBuilder> {

  public DeactivationReasonTtlvDeserializer() {
    super(DeactivationReason.kmipTag, DeactivationReason.encodingType);
  }

  @Override
  protected DeactivationReason.DeactivationReasonBuilder createBuilder() {
    return DeactivationReason.builder();
  }

  @Override
  protected void setValue(DeactivationReason.DeactivationReasonBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag == DeactivationReasonCode.kmipTag.getValue()) {
      builder.deactivationReasonCode(mapper.readValue(p, DeactivationReasonCode.class));
    } else if (nodeTag == DeactivationMessage.kmipTag.getValue()) {
      builder.deactivationMessage(mapper.readValue(p, DeactivationMessage.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeactivationReason build(DeactivationReason.DeactivationReasonBuilder builder) {
    return builder.build();
  }
}