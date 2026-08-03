package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SignOpResponsePayload;
import org.purpleBean.kmip.model.v2x1.type.CorrelationValue;

public class SignOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SignOpResponsePayload,
        SignOpResponsePayload.SignOpResponsePayloadBuilder> {

  public SignOpResponsePayloadTtlvDeserializer() {
    super(SignOpResponsePayload.kmipTag, SignOpResponsePayload.encodingType);
  }

  @Override
  protected SignOpResponsePayload.SignOpResponsePayloadBuilder createBuilder() {
    return SignOpResponsePayload.builder();
  }

  @Override
  protected void setValue(SignOpResponsePayload.SignOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.SIGNATURE_DATA ->
          builder.signatureData(mapper.readValue(p, SignatureData.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(mapper.readValue(p, CorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SignOpResponsePayload build(
      SignOpResponsePayload.SignOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
