package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;

import java.io.IOException;

public class SplitKeyPolynomialJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SplitKeyPolynomial, SplitKeyPolynomial.SplitKeyPolynomialBuilder> {

    public SplitKeyPolynomialJsonDeserializer() {
        super(SplitKeyPolynomial.kmipTag, SplitKeyPolynomial.encodingType);
    }

    @Override
    protected SplitKeyPolynomial.SplitKeyPolynomialBuilder createBuilder() {
        return SplitKeyPolynomial.builder();
    }

    @Override
    protected void setValue(SplitKeyPolynomial.SplitKeyPolynomialBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(SplitKeyPolynomial.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected SplitKeyPolynomial build(SplitKeyPolynomial.SplitKeyPolynomialBuilder builder) {
        return builder.build();
    }
}
