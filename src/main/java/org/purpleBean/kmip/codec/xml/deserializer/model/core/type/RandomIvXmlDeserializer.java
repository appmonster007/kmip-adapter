package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.RandomIv;

public class RandomIvXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<RandomIv, RandomIv.RandomIvBuilder> {

  public RandomIvXmlDeserializer() {
    super(RandomIv.kmipTag, RandomIv.encodingType);
  }

  @Override
  protected RandomIv.RandomIvBuilder createBuilder() {
    return RandomIv.builder();
  }

  @Override
  protected void setValue(RandomIv.RandomIvBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected RandomIv build(RandomIv.RandomIvBuilder builder) {
    return builder.build();
  }
}