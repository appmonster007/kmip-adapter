package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ValidateOpResponsePayload;

/**
 * TTLV deserializer for {@link ValidateOpResponsePayload}.
 */
public class ValidateOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidateOpResponsePayload,
        ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ValidateOpResponsePayloadTtlvDeserializer}.
   */
  public ValidateOpResponsePayloadTtlvDeserializer() {
    super(ValidateOpResponsePayload.kmipTag, ValidateOpResponsePayload.encodingType);
  }

  @Override
  protected ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder createBuilder() {
    return ValidateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.VALIDITY_INDICATOR)) {
      builder.validityIndicator(mapper.readValue(p, ValidityIndicator.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ValidateOpResponsePayload build(
      ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
