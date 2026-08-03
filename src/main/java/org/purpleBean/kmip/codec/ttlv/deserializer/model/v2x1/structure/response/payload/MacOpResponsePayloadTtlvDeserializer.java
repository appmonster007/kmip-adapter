package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.MacOpResponsePayload;
import org.purpleBean.kmip.model.v2x1.type.CorrelationValue;

public class MacOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MacOpResponsePayload,
        MacOpResponsePayload.MacOpResponsePayloadBuilder> {

  public MacOpResponsePayloadTtlvDeserializer() {
    super(MacOpResponsePayload.kmipTag, MacOpResponsePayload.encodingType);
  }

  @Override
  protected MacOpResponsePayload.MacOpResponsePayloadBuilder createBuilder() {
    return MacOpResponsePayload.builder();
  }

  @Override
  protected void setValue(MacOpResponsePayload.MacOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.MAC_DATA -> builder.macData(mapper.readValue(p, MacData.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(mapper.readValue(p, CorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MacOpResponsePayload build(MacOpResponsePayload.MacOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
