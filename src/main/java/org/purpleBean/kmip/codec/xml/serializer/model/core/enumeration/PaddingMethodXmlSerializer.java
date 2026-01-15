package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;

public class PaddingMethodXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PaddingMethod, String> {

    public PaddingMethodXmlSerializer() {
        super(PaddingMethod::getDescription);
    }
}