package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;

public class SimpleRequestBatchItemXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SimpleRequestBatchItem,
        SimpleRequestBatchItem.SimpleRequestBatchItemBuilder> {

  public SimpleRequestBatchItemXmlDeserializer() {
    super(SimpleRequestBatchItem.kmipTag, SimpleRequestBatchItem.encodingType);
  }

  @Override
  protected SimpleRequestBatchItem.SimpleRequestBatchItemBuilder createBuilder() {
    return SimpleRequestBatchItem.builder();
  }

  @Override
  protected void setValue(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.requestPayloadStructure(ctxt.readValue(p, RequestPayloadStructure.class));
  }

  @Override
  protected SimpleRequestBatchItem build(
      SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder) {
    return builder.build();
  }
}