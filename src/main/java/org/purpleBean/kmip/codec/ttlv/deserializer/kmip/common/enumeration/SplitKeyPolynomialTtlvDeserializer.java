package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SplitKeyPolynomial, Integer> {

    public SplitKeyPolynomialTtlvDeserializer() {
        super(SplitKeyPolynomial.kmipTag, SplitKeyPolynomial.encodingType, Integer.class, value -> new SplitKeyPolynomial(SplitKeyPolynomial.fromValue(value)));
    }
}