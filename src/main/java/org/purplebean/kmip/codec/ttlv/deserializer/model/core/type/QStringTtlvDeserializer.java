package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.QString;

public class QStringTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<QString, QString.QStringBuilder> {

  public QStringTtlvDeserializer() {
    super(QString.kmipTag, QString.encodingType);
  }

  @Override
  protected QString.QStringBuilder createBuilder() {
    return QString.builder();
  }

  @Override
  protected void setValue(QString.QStringBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected QString build(QString.QStringBuilder builder) {
    return builder.build();
  }
}
