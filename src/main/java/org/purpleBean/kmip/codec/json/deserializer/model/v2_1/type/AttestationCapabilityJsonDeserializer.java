package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.AttestationCapability;

public class AttestationCapabilityJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AttestationCapability,
        AttestationCapability.AttestationCapabilityBuilder> {

  public AttestationCapabilityJsonDeserializer() {
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