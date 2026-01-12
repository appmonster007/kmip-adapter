package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialJsonSerializer extends AbstractKmipJsonSerializer<SplitKeyPolynomial, String> {

    public SplitKeyPolynomialJsonSerializer() {
        super(SplitKeyPolynomial::getDescription);
    }
}