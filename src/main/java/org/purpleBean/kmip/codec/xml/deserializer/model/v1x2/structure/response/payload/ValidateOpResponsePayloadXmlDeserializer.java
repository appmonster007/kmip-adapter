package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ValidateOpResponsePayload;

public class ValidateOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidateOpResponsePayload,
        ValidateOpResponsePayload.ValidateOpResponsePayloadBuilder> {

  public ValidateOpResponsePayloadXmlDeserializer() {
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
