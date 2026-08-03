package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AttestationCapableIndicator;

/**
 * JSON deserializer for {@link AttestationCapableIndicator}.
 */
public class AttestationCapableIndicatorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AttestationCapableIndicator,
        AttestationCapableIndicator.AttestationCapableIndicatorBuilder> {

  /**
   * Constructs a new {@link AttestationCapableIndicatorJsonDeserializer}.
   */
  public AttestationCapableIndicatorJsonDeserializer() {
    super(AttestationCapableIndicator.kmipTag, AttestationCapableIndicator.encodingType);
  }

  @Override
  protected AttestationCapableIndicator.AttestationCapableIndicatorBuilder createBuilder() {
    return AttestationCapableIndicator.builder();
  }

  @Override
  protected void setValue(AttestationCapableIndicator.AttestationCapableIndicatorBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected AttestationCapableIndicator build(
      AttestationCapableIndicator.AttestationCapableIndicatorBuilder builder) {
    return builder.build();
  }
}
