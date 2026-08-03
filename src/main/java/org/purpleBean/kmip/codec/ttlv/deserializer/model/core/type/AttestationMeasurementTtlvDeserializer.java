package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

public class AttestationMeasurementTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttestationMeasurement,
        AttestationMeasurement.AttestationMeasurementBuilder> {

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