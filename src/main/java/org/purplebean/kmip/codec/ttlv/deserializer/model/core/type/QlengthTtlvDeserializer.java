package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.Qlength;

/**
 * TTLV deserializer for {@link Qlength}.
 */
public class QlengthTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Qlength, Qlength.QlengthBuilder> {

  /**
   * Constructs a new {@link QlengthTtlvDeserializer}.
   */
  public QlengthTtlvDeserializer() {
    super(Qlength.kmipTag, Qlength.encodingType);
  }

  @Override
  protected Qlength.QlengthBuilder createBuilder() {
    return Qlength.builder();
  }

  @Override
  protected void setValue(Qlength.QlengthBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected Qlength build(Qlength.QlengthBuilder builder) {
    return builder.build();
  }
}
