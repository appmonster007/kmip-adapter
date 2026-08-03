package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.enumeration.DeactivationReasonCode;
import org.purplebean.kmip.model.v3x0.structure.DeactivationReason;
import org.purplebean.kmip.model.v3x0.type.DeactivationMessage;

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