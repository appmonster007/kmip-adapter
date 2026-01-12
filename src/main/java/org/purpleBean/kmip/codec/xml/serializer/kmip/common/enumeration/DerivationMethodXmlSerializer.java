package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

public class DerivationMethodXmlSerializer extends AbstractKmipXmlSerializer<DerivationMethod, String> {

    public DerivationMethodXmlSerializer() {
        super(DerivationMethod::getDescription);
    }
}