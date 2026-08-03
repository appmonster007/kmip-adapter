package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.PutFunction;

/**
 * XML deserializer for {@link PutFunction}.
 */
public class PutFunctionXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<PutFunction, PutFunction.PutFunctionBuilder> {

  /**
   * Constructs a new {@link PutFunctionXmlDeserializer}.
   */
  public PutFunctionXmlDeserializer() {
    super(PutFunction.kmipTag, PutFunction.encodingType);
  }

  @Override
  protected PutFunction.PutFunctionBuilder createBuilder() {
    return PutFunction.builder();
  }

  @Override
  protected void setValue(PutFunction.PutFunctionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(PutFunction.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected PutFunction build(PutFunction.PutFunctionBuilder builder) {
    return builder.build();
  }
}