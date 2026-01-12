package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.WrappingMethod;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class WrappingMethodXmlSerializer extends AbstractKmipXmlSerializer<WrappingMethod, String> {

    public WrappingMethodXmlSerializer() {
        super(WrappingMethod::getDescription);
    }
}