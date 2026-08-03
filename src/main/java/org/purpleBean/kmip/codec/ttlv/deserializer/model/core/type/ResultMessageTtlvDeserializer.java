package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ResultMessage;

public class ResultMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ResultMessage, ResultMessage.ResultMessageBuilder> {

  public ResultMessageTtlvDeserializer() {
    super(ResultMessage.kmipTag, ResultMessage.encodingType);
  }

  @Override
  protected ResultMessage.ResultMessageBuilder createBuilder() {
    return ResultMessage.builder();
  }

  @Override
  protected void setValue(ResultMessage.ResultMessageBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected ResultMessage build(ResultMessage.ResultMessageBuilder builder) {
    return builder.build();
  }
}
