package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

public class InteropFunctionXmlSerializer extends AbstractKmipXmlSerializer<InteropFunction, String> {

    public InteropFunctionXmlSerializer() {
        super(InteropFunction::getDescription);
    }
}