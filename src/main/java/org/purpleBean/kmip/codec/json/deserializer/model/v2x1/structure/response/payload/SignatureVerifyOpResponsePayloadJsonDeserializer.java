package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SignatureVerifyOpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;

public class SignatureVerifyOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SignatureVerifyOpResponsePayload,
        SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder> {

  public SignatureVerifyOpResponsePayloadJsonDeserializer() {
    super(SignatureVerifyOpResponsePayload.kmipTag, SignatureVerifyOpResponsePayload.encodingType);
  }

  @Override
  protected SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder createBuilder() {
    return SignatureVerifyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.VALIDITY_INDICATOR ->
          builder.validityIndicator(ctxt.readValue(p, ValidityIndicator.class));
      case KmipTag.Standard.DATA -> builder.data(ctxt.readValue(p, DataByteString.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SignatureVerifyOpResponsePayload build(
      SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
