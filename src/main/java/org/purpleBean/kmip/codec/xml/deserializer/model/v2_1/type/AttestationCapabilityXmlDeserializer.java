package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.AttestationCapability;

public class AttestationCapabilityXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<AttestationCapability,
        AttestationCapability.AttestationCapabilityBuilder> {

  public AttestationCapabilityXmlDeserializer() {
    super(AttestationCapability.kmipTag, AttestationCapability.encodingType);
  }

  @Override
  protected AttestationCapability.AttestationCapabilityBuilder createBuilder() {
    return AttestationCapability.builder();
  }

  @Override
  protected void setValue(AttestationCapability.AttestationCapabilityBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected AttestationCapability build(
      AttestationCapability.AttestationCapabilityBuilder builder) {
    return builder.build();
  }
}