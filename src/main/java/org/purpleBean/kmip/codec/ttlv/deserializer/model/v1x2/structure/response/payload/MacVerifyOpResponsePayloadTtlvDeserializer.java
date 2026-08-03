package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.MacVerifyOpResponsePayload;

public class MacVerifyOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MacVerifyOpResponsePayload,
        MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder> {

  public MacVerifyOpResponsePayloadTtlvDeserializer() {
    super(MacVerifyOpResponsePayload.kmipTag, MacVerifyOpResponsePayload.encodingType);
  }

  @Override
  protected MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder createBuilder() {
    return MacVerifyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.VALIDITY_INDICATOR ->
          builder.validityIndicator(mapper.readValue(p, ValidityIndicator.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MacVerifyOpResponsePayload build(
      MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
