package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AttestationMeasurement;

/**
 * JSON deserializer for {@link AttestationMeasurement}.
 */
public class AttestationMeasurementJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AttestationMeasurement,
        AttestationMeasurement.AttestationMeasurementBuilder> {

  /**
   * Constructs a new {@link AttestationMeasurementJsonDeserializer}.
   */
  public AttestationMeasurementJsonDeserializer() {
    super(AttestationMeasurement.kmipTag, AttestationMeasurement.encodingType);
  }

  @Override
  protected AttestationMeasurement.AttestationMeasurementBuilder createBuilder() {
    return AttestationMeasurement.builder();
  }

  @Override
  protected void setValue(AttestationMeasurement.AttestationMeasurementBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected AttestationMeasurement build(
      AttestationMeasurement.AttestationMeasurementBuilder builder) {
    return builder.build();
  }
}
