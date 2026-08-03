package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.SignatureData;

public class SignatureDataTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SignatureData, SignatureData.SignatureDataBuilder> {

  public SignatureDataTtlvDeserializer() {
    super(SignatureData.kmipTag, SignatureData.encodingType);
  }

  @Override
  protected SignatureData.SignatureDataBuilder createBuilder() {
    return SignatureData.builder();
  }

  @Override
  protected void setValue(SignatureData.SignatureDataBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected SignatureData build(SignatureData.SignatureDataBuilder builder) {
    return builder.build();
  }
}
