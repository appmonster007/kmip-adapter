package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodXmlSerializer extends AbstractKmipDataTypeXmlSerializer<WrappingMethod, String> {

    public WrappingMethodXmlSerializer() {
        super(WrappingMethod::getDescription);
    }
}