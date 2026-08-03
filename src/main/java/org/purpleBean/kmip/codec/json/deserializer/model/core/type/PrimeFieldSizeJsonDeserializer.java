package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;

public class PrimeFieldSizeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PrimeFieldSize, PrimeFieldSize.PrimeFieldSizeBuilder> {

  public PrimeFieldSizeJsonDeserializer() {
    super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType);
  }

  @Override
  protected PrimeFieldSize.PrimeFieldSizeBuilder createBuilder() {
    return PrimeFieldSize.builder();
  }

  @Override
  protected void setValue(PrimeFieldSize.PrimeFieldSizeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected PrimeFieldSize build(PrimeFieldSize.PrimeFieldSizeBuilder builder) {
    return builder.build();
  }
}
