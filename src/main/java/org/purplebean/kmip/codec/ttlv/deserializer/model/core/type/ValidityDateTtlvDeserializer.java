package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ValidityDate;

/**
 * TTLV deserializer for {@link ValidityDate}.
 */
public class ValidityDateTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ValidityDate, ValidityDate.ValidityDateBuilder> {

  /**
   * Constructs a new {@link ValidityDateTtlvDeserializer}.
   */
  public ValidityDateTtlvDeserializer() {
    super(ValidityDate.kmipTag, ValidityDate.encodingType);
  }

  @Override
  protected ValidityDate.ValidityDateBuilder createBuilder() {
    return ValidityDate.builder();
  }

  @Override
  protected void setValue(ValidityDate.ValidityDateBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected ValidityDate build(ValidityDate.ValidityDateBuilder builder) {
    return builder.build();
  }
}