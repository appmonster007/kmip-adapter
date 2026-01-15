package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.InteropFunction;

public class InteropFunctionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InteropFunction, String> {

    public InteropFunctionXmlSerializer() {
        super(InteropFunction::getDescription);
    }
}