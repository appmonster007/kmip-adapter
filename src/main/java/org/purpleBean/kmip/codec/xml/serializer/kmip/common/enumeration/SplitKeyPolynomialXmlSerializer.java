package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class SplitKeyPolynomialXmlSerializer extends AbstractKmipXmlSerializer<SplitKeyPolynomial, String> {

    public SplitKeyPolynomialXmlSerializer() {
        super(SplitKeyPolynomial::getDescription);
    }
}