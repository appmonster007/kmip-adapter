package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SplitKeyPolynomial, String> {

    public SplitKeyPolynomialXmlSerializer() {
        super(SplitKeyPolynomial::getDescription);
    }
}