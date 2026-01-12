package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.PutFunction;

public class PutFunctionXmlSerializer extends AbstractKmipXmlSerializer<PutFunction, String> {

    public PutFunctionXmlSerializer() {
        super(PutFunction::getDescription);
    }
}