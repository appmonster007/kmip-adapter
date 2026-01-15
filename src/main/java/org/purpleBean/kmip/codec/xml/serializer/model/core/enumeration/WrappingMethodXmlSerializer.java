package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;

public class WrappingMethodXmlSerializer extends AbstractKmipDataTypeXmlSerializer<WrappingMethod, String> {

    public WrappingMethodXmlSerializer() {
        super(WrappingMethod::getDescription);
    }
}