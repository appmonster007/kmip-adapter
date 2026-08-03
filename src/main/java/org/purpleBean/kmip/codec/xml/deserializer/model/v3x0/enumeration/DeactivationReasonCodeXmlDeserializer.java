package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3x0.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DeactivationReasonCode,
        DeactivationReasonCode.DeactivationReasonCodeBuilder> {

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