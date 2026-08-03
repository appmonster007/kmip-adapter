package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SplitKeyPolynomial,
        SplitKeyPolynomial.SplitKeyPolynomialBuilder> {

  public SplitKeyPolynomialTtlvDeserializer() {
    super(SplitKeyPolynomial.kmipTag, SplitKeyPolynomial.encodingType);
  }

  @Override
  protected SplitKeyPolynomial.SplitKeyPolynomialBuilder createBuilder() {
    return SplitKeyPolynomial.builder();
  }

  @Override
  protected void setValue(SplitKeyPolynomial.SplitKeyPolynomialBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(SplitKeyPolynomial.fromValue(value));
  }

  @Override
  protected SplitKeyPolynomial build(SplitKeyPolynomial.SplitKeyPolynomialBuilder builder) {
    return builder.build();
  }
}
