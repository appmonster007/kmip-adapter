package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AttestationAssertion;

public class AttestationAssertionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AttestationAssertion,
        AttestationAssertion.AttestationAssertionBuilder> {

  public AttestationAssertionJsonDeserializer() {
    super(AttestationAssertion.kmipTag, AttestationAssertion.encodingType);
  }

  @Override
  protected AttestationAssertion.AttestationAssertionBuilder createBuilder() {
    return AttestationAssertion.builder();
  }

  @Override
  protected void setValue(AttestationAssertion.AttestationAssertionBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected AttestationAssertion build(AttestationAssertion.AttestationAssertionBuilder builder) {
    return builder.build();
  }
}
