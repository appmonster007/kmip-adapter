package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purplebean.kmip.model.core.structure.RevocationReason;
import org.purplebean.kmip.model.core.type.RevocationMessage;

/**
 * TTLV deserializer for {@link RevocationReason}.
 */
public class RevocationReasonTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RevocationReason,
        RevocationReason.RevocationReasonBuilder> {

  /**
   * Constructs a new {@link RevocationReasonTtlvDeserializer}.
   */
  public RevocationReasonTtlvDeserializer() {
    super(RevocationReason.kmipTag, RevocationReason.encodingType);
  }

  @Override
  protected RevocationReason.RevocationReasonBuilder createBuilder() {
    return RevocationReason.builder();
  }

  @Override
  protected void setValue(RevocationReason.RevocationReasonBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.REVOCATION_REASON_CODE ->
          builder.revocationReasonCode(mapper.readValue(p, RevocationReasonCode.class));
      case KmipTag.Standard.REVOCATION_MESSAGE ->
          builder.revocationMessage(mapper.readValue(p, RevocationMessage.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RevocationReason build(RevocationReason.RevocationReasonBuilder builder) {
    return builder.build();
  }
}