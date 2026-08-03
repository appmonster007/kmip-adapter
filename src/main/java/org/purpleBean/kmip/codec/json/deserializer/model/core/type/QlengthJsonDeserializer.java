package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Qlength;

public class QlengthJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Qlength, Qlength.QlengthBuilder> {

  public QlengthJsonDeserializer() {
    super(Qlength.kmipTag, Qlength.encodingType);
  }

  @Override
  protected Qlength.QlengthBuilder createBuilder() {
    return Qlength.builder();
  }

  @Override
  protected void setValue(Qlength.QlengthBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected Qlength build(Qlength.QlengthBuilder builder) {
    return builder.build();
  }
}
