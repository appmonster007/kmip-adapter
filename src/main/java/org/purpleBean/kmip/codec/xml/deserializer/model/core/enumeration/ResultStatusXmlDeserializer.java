package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;

public class ResultStatusXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ResultStatus, ResultStatus.ResultStatusBuilder> {

  public ResultStatusXmlDeserializer() {
    super(ResultStatus.kmipTag, ResultStatus.encodingType);
  }

  @Override
  protected ResultStatus.ResultStatusBuilder createBuilder() {
    return ResultStatus.builder();
  }

  @Override
  protected void setValue(ResultStatus.ResultStatusBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ResultStatus.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ResultStatus build(ResultStatus.ResultStatusBuilder builder) {
    return builder.build();
  }
}