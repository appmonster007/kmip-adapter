package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.SignatureVerifyOpResponsePayload;

public class SignatureVerifyOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SignatureVerifyOpResponsePayload,
        SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder> {

  public SignatureVerifyOpResponsePayloadXmlDeserializer() {
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
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SignatureVerifyOpResponsePayload build(
      SignatureVerifyOpResponsePayload.SignatureVerifyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
