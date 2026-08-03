package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SignatureVerifyOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2_1.type.DigestedData;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

public class SignatureVerifyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SignatureVerifyOpRequestPayload,
        SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder> {

  public SignatureVerifyOpRequestPayloadTtlvDeserializer() {
    super(SignatureVerifyOpRequestPayload.kmipTag, SignatureVerifyOpRequestPayload.encodingType);
  }

  @Override
  protected SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder createBuilder() {
    return SignatureVerifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
      case KmipTag.Standard.DIGESTED_DATA ->
          builder.digestedData(mapper.readValue(p, DigestedData.class));
      case KmipTag.Standard.SIGNATURE_DATA ->
          builder.signatureData(mapper.readValue(p, SignatureData.class));
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
  protected SignatureVerifyOpRequestPayload build(
      SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
