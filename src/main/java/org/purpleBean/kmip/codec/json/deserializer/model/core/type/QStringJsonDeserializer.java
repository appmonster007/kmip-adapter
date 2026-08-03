package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.QString;

public class QStringJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<QString, QString.QStringBuilder> {

  public QStringJsonDeserializer() {
    super(QString.kmipTag, QString.encodingType);
  }

  @Override
  protected QString.QStringBuilder createBuilder() {
    return QString.builder();
  }

  @Override
  protected void setValue(QString.QStringBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected QString build(QString.QStringBuilder builder) {
    return builder.build();
  }
}
