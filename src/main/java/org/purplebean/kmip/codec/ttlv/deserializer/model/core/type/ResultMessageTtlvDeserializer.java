package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ResultMessage;

/**
 * TTLV deserializer for {@link ResultMessage}.
 */
public class ResultMessageTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ResultMessage, ResultMessage.ResultMessageBuilder> {

  /**
   * Constructs a new {@link ResultMessageTtlvDeserializer}.
   */
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
