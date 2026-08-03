package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.RevocationReason;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RevokeOpRequestPayload;

public class RevokeOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RevokeOpRequestPayload,
        RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder> {

  public RevokeOpRequestPayloadTtlvDeserializer() {
    super(RevokeOpRequestPayload.kmipTag, RevokeOpRequestPayload.encodingType);
  }

  @Override
  protected RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder createBuilder() {
    return RevokeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.REVOCATION_REASON ->
          builder.revocationReason(mapper.readValue(p, RevocationReason.class));
      case KmipTag.Standard.COMPROMISE_OCCURRENCE_DATE ->
          builder.compromiseOccurrenceDate(mapper.readValue(p, CompromiseOccurrenceDate.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RevokeOpRequestPayload build(
      RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
