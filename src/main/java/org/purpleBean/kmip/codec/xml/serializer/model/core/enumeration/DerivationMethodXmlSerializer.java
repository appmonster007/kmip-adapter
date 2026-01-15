package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;

public class DerivationMethodXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DerivationMethod, String> {

    public DerivationMethodXmlSerializer() {
        super(DerivationMethod::getDescription);
    }
}