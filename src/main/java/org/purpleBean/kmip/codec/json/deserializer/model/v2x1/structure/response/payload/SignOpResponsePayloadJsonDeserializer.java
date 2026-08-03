package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SignOpResponsePayload;
import org.purpleBean.kmip.model.v2x1.type.CorrelationValue;

public class SignOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SignOpResponsePayload,
        SignOpResponsePayload.SignOpResponsePayloadBuilder> {

  public SignOpResponsePayloadJsonDeserializer() {
    super(SignOpResponsePayload.kmipTag, SignOpResponsePayload.encodingType);
  }

  @Override
  protected SignOpResponsePayload.SignOpResponsePayloadBuilder createBuilder() {
    return SignOpResponsePayload.builder();
  }

  @Override
  protected void setValue(SignOpResponsePayload.SignOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.SIGNATURE_DATA ->
          builder.signatureData(ctxt.readValue(p, SignatureData.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SignOpResponsePayload build(
      SignOpResponsePayload.SignOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
