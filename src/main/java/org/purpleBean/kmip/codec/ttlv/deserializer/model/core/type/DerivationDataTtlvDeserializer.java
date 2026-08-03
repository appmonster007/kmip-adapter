package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DerivationData;

public class DerivationDataTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DerivationData, DerivationData.DerivationDataBuilder> {

  public DerivationDataTtlvDeserializer() {
    super(DerivationData.kmipTag, DerivationData.encodingType);
  }

  @Override
  protected DerivationData.DerivationDataBuilder createBuilder() {
    return DerivationData.builder();
  }

  @Override
  protected void setValue(DerivationData.DerivationDataBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected DerivationData build(DerivationData.DerivationDataBuilder builder) {
    return builder.build();
  }
}
