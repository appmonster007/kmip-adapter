package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.SubmissionDate;

public class SubmissionDateTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SubmissionDate, SubmissionDate.SubmissionDateBuilder> {

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