package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SplitKeyPolynomial, String> {

    public SplitKeyPolynomialJsonSerializer() {
        super(SplitKeyPolynomial::getDescription);
    }
}