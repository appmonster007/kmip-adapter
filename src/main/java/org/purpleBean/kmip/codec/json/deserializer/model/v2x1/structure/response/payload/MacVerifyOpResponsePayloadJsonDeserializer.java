package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.MacVerifyOpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;

public class MacVerifyOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MacVerifyOpResponsePayload,
        MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder> {

  public MacVerifyOpResponsePayloadJsonDeserializer() {
    super(MacVerifyOpResponsePayload.kmipTag, MacVerifyOpResponsePayload.encodingType);
  }

  @Override
  protected MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder createBuilder() {
    return MacVerifyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.VALIDITY_INDICATOR ->
          builder.validityIndicator(ctxt.readValue(p, ValidityIndicator.class));
      case KmipTag.Standard.CORRELATION_VALUE ->
          builder.correlationValue(ctxt.readValue(p, CorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MacVerifyOpResponsePayload build(
      MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
