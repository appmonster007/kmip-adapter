package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SplitKeyPolynomial, String> {

    public SplitKeyPolynomialXmlSerializer() {
        super(SplitKeyPolynomial::getDescription);
    }
}