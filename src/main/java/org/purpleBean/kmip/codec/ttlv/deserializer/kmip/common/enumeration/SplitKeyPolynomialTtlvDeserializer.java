package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialTtlvDeserializer extends AbstractKmipTtlvDeserializer<SplitKeyPolynomial, Integer> {

    public SplitKeyPolynomialTtlvDeserializer() {
        super(SplitKeyPolynomial.kmipTag, SplitKeyPolynomial.encodingType, Integer.class, value -> new SplitKeyPolynomial(SplitKeyPolynomial.fromValue(value)));
    }
}