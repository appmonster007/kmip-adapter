package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.ValidateOpResponsePayload;

public class ValidateOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidateOpResponsePayload,
        ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder> {

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
