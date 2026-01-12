package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodXmlSerializer extends AbstractKmipXmlSerializer<WrappingMethod, String> {

    public WrappingMethodXmlSerializer() {
        super(WrappingMethod::getDescription);
    }
}