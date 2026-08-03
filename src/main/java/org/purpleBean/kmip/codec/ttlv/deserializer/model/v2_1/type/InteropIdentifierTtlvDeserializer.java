package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.InteropIdentifier;

public class InteropIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<InteropIdentifier,
        InteropIdentifier.InteropIdentifierBuilder> {

  public InteropIdentifierTtlvDeserializer() {
    super(InteropIdentifier.kmipTag, InteropIdentifier.encodingType);
  }

  @Override
  protected InteropIdentifier.InteropIdentifierBuilder createBuilder() {
    return InteropIdentifier.builder();
  }

  @Override
  protected void setValue(InteropIdentifier.InteropIdentifierBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected InteropIdentifier build(InteropIdentifier.InteropIdentifierBuilder builder) {
    return builder.build();
  }
}
