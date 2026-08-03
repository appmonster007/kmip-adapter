package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AlternativeNameValue;

public class AlternativeNameValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AlternativeNameValue,
        AlternativeNameValue.AlternativeNameValueBuilder> {

  public AlternativeNameValueTtlvDeserializer() {
    super(AlternativeNameValue.kmipTag, AlternativeNameValue.encodingType);
  }

  @Override
  protected AlternativeNameValue.AlternativeNameValueBuilder createBuilder() {
    return AlternativeNameValue.builder();
  }

  @Override
  protected void setValue(AlternativeNameValue.AlternativeNameValueBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected AlternativeNameValue build(AlternativeNameValue.AlternativeNameValueBuilder builder) {
    return builder.build();
  }
}