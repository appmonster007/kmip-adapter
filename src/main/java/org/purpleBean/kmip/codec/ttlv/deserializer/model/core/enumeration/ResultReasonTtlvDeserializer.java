package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ResultReason;

public class ResultReasonTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ResultReason, ResultReason.ResultReasonBuilder> {

  public ResultReasonTtlvDeserializer() {
    super(ResultReason.kmipTag, ResultReason.encodingType);
  }

  @Override
  protected ResultReason.ResultReasonBuilder createBuilder() {
    return ResultReason.builder();
  }

  @Override
  protected void setValue(ResultReason.ResultReasonBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ResultReason.fromValue(value));
  }

  @Override
  protected ResultReason build(ResultReason.ResultReasonBuilder builder) {
    return builder.build();
  }
}
