package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.PaddingMethod;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class PaddingMethodXmlSerializer extends AbstractKmipXmlSerializer<PaddingMethod, String> {

    public PaddingMethodXmlSerializer() {
        super(PaddingMethod::getDescription);
    }
}