package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SplitKeyPolynomial, String> {

    public SplitKeyPolynomialJsonSerializer() {
        super(SplitKeyPolynomial::getDescription);
    }
}