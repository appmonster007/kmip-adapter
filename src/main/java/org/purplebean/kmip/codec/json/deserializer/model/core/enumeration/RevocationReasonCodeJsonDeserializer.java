package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;

public class RevocationReasonCodeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RevocationReasonCode,
        RevocationReasonCode.RevocationReasonCodeBuilder> {

  public RevocationReasonCodeJsonDeserializer() {
    super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType);
  }

  @Override
  protected RevocationReasonCode.RevocationReasonCodeBuilder createBuilder() {
    return RevocationReasonCode.builder();
  }

  @Override
  protected void setValue(RevocationReasonCode.RevocationReasonCodeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(RevocationReasonCode.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected RevocationReasonCode build(RevocationReasonCode.RevocationReasonCodeBuilder builder) {
    return builder.build();
  }
}
