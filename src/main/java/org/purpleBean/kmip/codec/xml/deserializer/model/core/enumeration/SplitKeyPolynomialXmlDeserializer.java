package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SplitKeyPolynomial, String> {

    public SplitKeyPolynomialXmlDeserializer() {
        super(SplitKeyPolynomial.kmipTag, SplitKeyPolynomial.encodingType, String.class, value -> new SplitKeyPolynomial(SplitKeyPolynomial.fromName(value)));
    }
}