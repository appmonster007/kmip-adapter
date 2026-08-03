package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AttestationMeasurement;

/**
 * TTLV deserializer for {@link AttestationMeasurement}.
 */
public class AttestationMeasurementTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttestationMeasurement,
        AttestationMeasurement.AttestationMeasurementBuilder> {

  /**
   * Constructs a new {@link AttestationMeasurementTtlvDeserializer}.
   */
  public AttestationMeasurementTtlvDeserializer() {
    super(AttestationMeasurement.kmipTag, AttestationMeasurement.encodingType);
  }

  @Override
  protected AttestationMeasurement.AttestationMeasurementBuilder createBuilder() {
    return AttestationMeasurement.builder();
  }

  @Override
  protected void setValue(AttestationMeasurement.AttestationMeasurementBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected AttestationMeasurement build(
      AttestationMeasurement.AttestationMeasurementBuilder builder) {
    return builder.build();
  }
}