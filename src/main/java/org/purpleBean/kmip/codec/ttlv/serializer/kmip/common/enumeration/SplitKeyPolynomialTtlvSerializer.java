package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialTtlvSerializer extends AbstractKmipTtlvSerializer<SplitKeyPolynomial, Integer> {

    public SplitKeyPolynomialTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}