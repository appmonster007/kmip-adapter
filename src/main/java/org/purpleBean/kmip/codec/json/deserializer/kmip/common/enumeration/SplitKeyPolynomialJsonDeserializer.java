package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialJsonDeserializer extends AbstractKmipJsonDeserializer<SplitKeyPolynomial, String> {

    public SplitKeyPolynomialJsonDeserializer() {
        super(SplitKeyPolynomial.kmipTag, SplitKeyPolynomial.encodingType, String.class, value -> new SplitKeyPolynomial(SplitKeyPolynomial.fromName(value)));
    }
}