package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.DigestedData;

public class DigestedDataTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<DigestedData, DigestedData.DigestedDataBuilder> {

  public DigestedDataTtlvDeserializer() {
    super(DigestedData.kmipTag, DigestedData.encodingType);
  }

  @Override
  protected DigestedData.DigestedDataBuilder createBuilder() {
    return DigestedData.builder();
  }

  @Override
  protected void setValue(DigestedData.DigestedDataBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected DigestedData build(DigestedData.DigestedDataBuilder builder) {
    return builder.build();
  }
}