package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.KeyValueByteString;

public class KeyValueByteStringTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyValueByteString,
        KeyValueByteString.KeyValueByteStringBuilder> {

  public KeyValueByteStringTtlvDeserializer() {
    super(KeyValueByteString.kmipTag, KeyValueByteString.encodingType);
  }

  @Override
  protected KeyValueByteString.KeyValueByteStringBuilder createBuilder() {
    return KeyValueByteString.builder();
  }

  @Override
  protected void setValue(KeyValueByteString.KeyValueByteStringBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected KeyValueByteString build(KeyValueByteString.KeyValueByteStringBuilder builder) {
    return builder.build();
  }
}
