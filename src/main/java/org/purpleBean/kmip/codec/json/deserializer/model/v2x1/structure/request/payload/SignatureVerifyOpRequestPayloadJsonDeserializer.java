package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.SignatureVerifyOpRequestPayload;
import org.purpleBean.kmip.model.v2x1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2x1.type.DigestedData;
import org.purpleBean.kmip.model.v2x1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2x1.type.InitIndicator;

public class SignatureVerifyOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SignatureVerifyOpRequestPayload,
        SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder> {

  public SignatureVerifyOpRequestPayloadJsonDeserializer() {
    super(SignatureVerifyOpRequestPayload.kmipTag, SignatureVerifyOpRequestPayload.encodingType);
  }

  @Override
  protected SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder createBuilder() {
    return SignatureVerifyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
      case KmipTag.Standard.DIGESTED_DATA ->
          builder.digestedData(ctxt.readValue(p, DigestedData.class));
      case KmipTag.Standard.SIGNATURE_DATA ->
          builder.signatureData(ctxt.readValue(p, SignatureData.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
      case KmipTag.Standard.INIT_INDICATOR ->
          builder.initIndicator(ctxt.readValue(p, InitIndicator.class));
      case KmipTag.Standard.FINAL_INDICATOR ->
          builder.finalIndicator(ctxt.readValue(p, FinalIndicator.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SignatureVerifyOpRequestPayload build(
      SignatureVerifyOpRequestPayload.SignatureVerifyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
