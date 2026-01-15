package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SplitKeyPolynomial, String> {

    public SplitKeyPolynomialJsonDeserializer() {
        super(SplitKeyPolynomial.kmipTag, SplitKeyPolynomial.encodingType, String.class, value -> new SplitKeyPolynomial(SplitKeyPolynomial.fromName(value)));
    }
}