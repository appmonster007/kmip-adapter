package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.MacOpRequestPayload;
import org.purpleBean.kmip.model.v2x1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2x1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2x1.type.InitIndicator;

public class MacOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MacOpRequestPayload,
        MacOpRequestPayload.MacOpRequestPayloadBuilder> {

  public MacOpRequestPayloadTtlvDeserializer() {
    super(MacOpRequestPayload.kmipTag, MacOpRequestPayload.encodingType);
  }

  @Override
  protected MacOpRequestPayload.MacOpRequestPayloadBuilder createBuilder() {
    return MacOpRequestPayload.builder();
  }

  @Override
  protected void setValue(MacOpRequestPayload.MacOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(mapper.readValue(p, CorrelationValue.class));
      case KmipTag.Standard.INIT_INDICATOR ->
          builder.initIndicator(mapper.readValue(p, InitIndicator.class));
      case KmipTag.Standard.FINAL_INDICATOR ->
          builder.finalIndicator(mapper.readValue(p, FinalIndicator.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MacOpRequestPayload build(MacOpRequestPayload.MacOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
