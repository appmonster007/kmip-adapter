package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousCorrelationValues;

public class AsynchronousCorrelationValuesJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AsynchronousCorrelationValues,
        AsynchronousCorrelationValues.AsynchronousCorrelationValuesBuilder> {

  public AsynchronousCorrelationValuesJsonDeserializer() {
    super(AsynchronousCorrelationValues.kmipTag, AsynchronousCorrelationValues.encodingType);
  }

  @Override
  protected AsynchronousCorrelationValues.AsynchronousCorrelationValuesBuilder createBuilder() {
    return AsynchronousCorrelationValues.builder();
  }

  @Override
  protected void setValue(
      AsynchronousCorrelationValues.AsynchronousCorrelationValuesBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          ctxt.readValue(p, AsynchronousCorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AsynchronousCorrelationValues build(
      AsynchronousCorrelationValues.AsynchronousCorrelationValuesBuilder builder) {
    return builder.build();
  }
}