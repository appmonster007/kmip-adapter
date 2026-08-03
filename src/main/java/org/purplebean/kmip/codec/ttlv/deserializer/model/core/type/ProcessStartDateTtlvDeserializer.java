package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ProcessStartDate;

/**
 * TTLV deserializer for {@link ProcessStartDate}.
 */
public class ProcessStartDateTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProcessStartDate,
        ProcessStartDate.ProcessStartDateBuilder> {

  /**
   * Constructs a new {@link ProcessStartDateTtlvDeserializer}.
   */
  public ProcessStartDateTtlvDeserializer() {
    super(ProcessStartDate.kmipTag, ProcessStartDate.encodingType);
  }

  @Override
  protected ProcessStartDate.ProcessStartDateBuilder createBuilder() {
    return ProcessStartDate.builder();
  }

  @Override
  protected void setValue(ProcessStartDate.ProcessStartDateBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
  }

  @Override
  protected ProcessStartDate build(ProcessStartDate.ProcessStartDateBuilder builder) {
    return builder.build();
  }
}