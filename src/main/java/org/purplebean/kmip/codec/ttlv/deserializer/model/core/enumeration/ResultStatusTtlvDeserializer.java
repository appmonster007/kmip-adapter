package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;

public class ResultStatusTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ResultStatus, ResultStatus.ResultStatusBuilder> {

  public ResultStatusTtlvDeserializer() {
    super(ResultStatus.kmipTag, ResultStatus.encodingType);
  }

  @Override
  protected ResultStatus.ResultStatusBuilder createBuilder() {
    return ResultStatus.builder();
  }

  @Override
  protected void setValue(ResultStatus.ResultStatusBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(ResultStatus.fromValue(value));
  }

  @Override
  protected ResultStatus build(ResultStatus.ResultStatusBuilder builder) {
    return builder.build();
  }
}
