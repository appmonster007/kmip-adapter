package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.MacData;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.MacVerifyOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.FinalIndicator;
import org.purplebean.kmip.model.v2x1.type.InitIndicator;

/**
 * TTLV deserializer for {@link MacVerifyOpRequestPayload}.
 */
public class MacVerifyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MacVerifyOpRequestPayload,
        MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link MacVerifyOpRequestPayloadTtlvDeserializer}.
   */
  public MacVerifyOpRequestPayloadTtlvDeserializer() {
    super(MacVerifyOpRequestPayload.kmipTag, MacVerifyOpRequestPayload.encodingType);
  }

  @Override
  protected MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder createBuilder() {
    return MacVerifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      case KmipTag.Standard.MAC_DATA -> builder.macData(mapper.readValue(p, MacData.class));
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
  protected MacVerifyOpRequestPayload build(
      MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
