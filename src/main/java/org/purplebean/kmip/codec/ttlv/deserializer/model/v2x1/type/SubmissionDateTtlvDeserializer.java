package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.SubmissionDate;

/**
 * TTLV deserializer for {@link SubmissionDate}.
 */
public class SubmissionDateTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SubmissionDate, SubmissionDate.SubmissionDateBuilder> {

  /**
   * Constructs a new {@link SubmissionDateTtlvDeserializer}.
   */
  public SubmissionDateTtlvDeserializer() {
    super(SubmissionDate.kmipTag, SubmissionDate.encodingType);
  }

  @Override
  protected SubmissionDate.SubmissionDateBuilder createBuilder() {
    return SubmissionDate.builder();
  }

  @Override
  protected void setValue(SubmissionDate.SubmissionDateBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected SubmissionDate build(SubmissionDate.SubmissionDateBuilder builder) {
    return builder.build();
  }
}