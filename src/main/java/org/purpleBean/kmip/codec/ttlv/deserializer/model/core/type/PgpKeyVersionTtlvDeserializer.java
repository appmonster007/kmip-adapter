package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyVersionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PgpKeyVersion, PgpKeyVersion.PgpKeyVersionBuilder> {

  public PgpKeyVersionTtlvDeserializer() {
    super(PgpKeyVersion.kmipTag, PgpKeyVersion.encodingType);
  }

  @Override
  protected PgpKeyVersion.PgpKeyVersionBuilder createBuilder() {
    return PgpKeyVersion.builder();
  }

  @Override
  protected void setValue(PgpKeyVersion.PgpKeyVersionBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected PgpKeyVersion build(PgpKeyVersion.PgpKeyVersionBuilder builder) {
    return builder.build();
  }
}