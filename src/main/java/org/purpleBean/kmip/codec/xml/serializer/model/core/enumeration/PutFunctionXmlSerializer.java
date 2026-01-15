package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;

public class PutFunctionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PutFunction, String> {

    public PutFunctionXmlSerializer() {
        super(PutFunction::getDescription);
    }
}