package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.enumeration.DeactivationReasonCode;

/**
 * XML deserializer for {@link DeactivationReasonCode}.
 */
public class DeactivationReasonCodeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DeactivationReasonCode,
        DeactivationReasonCode.DeactivationReasonCodeBuilder> {

  /**
   * Constructs a new {@link DeactivationReasonCodeXmlDeserializer}.
   */
  public DeactivationReasonCodeXmlDeserializer() {
    super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType);
  }

  @Override
  protected DeactivationReasonCode.DeactivationReasonCodeBuilder createBuilder() {
    return DeactivationReasonCode.builder();
  }

  @Override
  protected void setValue(DeactivationReasonCode.DeactivationReasonCodeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(DeactivationReasonCode.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected DeactivationReasonCode build(
      DeactivationReasonCode.DeactivationReasonCodeBuilder builder) {
    return builder.build();
  }
}