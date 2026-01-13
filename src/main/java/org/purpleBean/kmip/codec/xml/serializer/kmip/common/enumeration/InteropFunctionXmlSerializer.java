package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

public class InteropFunctionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InteropFunction, String> {

    public InteropFunctionXmlSerializer() {
        super(InteropFunction::getDescription);
    }
}