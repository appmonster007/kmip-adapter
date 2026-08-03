package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.type.NistSecurityCategory;

public class NistSecurityCategoryTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<NistSecurityCategory,
        NistSecurityCategory.NistSecurityCategoryBuilder> {

  public NistSecurityCategoryTtlvDeserializer() {
    super(NistSecurityCategory.kmipTag, NistSecurityCategory.encodingType);
  }

  @Override
  protected NistSecurityCategory.NistSecurityCategoryBuilder createBuilder() {
    return NistSecurityCategory.builder();
  }

  @Override
  protected void setValue(NistSecurityCategory.NistSecurityCategoryBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected NistSecurityCategory build(NistSecurityCategory.NistSecurityCategoryBuilder builder) {
    return builder.build();
  }
}