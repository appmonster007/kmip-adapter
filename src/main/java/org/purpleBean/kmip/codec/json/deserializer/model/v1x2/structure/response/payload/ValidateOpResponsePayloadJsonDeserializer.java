package org.purpleBean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.ValidateOpResponsePayload;

public class ValidateOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ValidateOpResponsePayload,
        ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder> {

  public ValidateOpResponsePayloadJsonDeserializer() {
    super(ValidateOpResponsePayload.kmipTag, ValidateOpResponsePayload.encodingType);
  }

  @Override
  protected ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder createBuilder() {
    return ValidateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag.equals(KmipTag.Standard.VALIDITY_INDICATOR)) {
      builder.validityIndicator(ctxt.readValue(p, ValidityIndicator.class));
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
