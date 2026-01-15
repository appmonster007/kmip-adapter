package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SplitKeyPolynomial, Integer> {

    public SplitKeyPolynomialTtlvSerializer() {
        super(SplitKeyPolynomial::getValue);
    }
}