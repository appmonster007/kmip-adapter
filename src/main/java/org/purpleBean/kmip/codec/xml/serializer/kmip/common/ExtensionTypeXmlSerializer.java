package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.ExtensionType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ExtensionTypeXmlSerializer extends AbstractKmipXmlSerializer<ExtensionType, Integer> {

    public ExtensionTypeXmlSerializer() {
        super(ExtensionType::getValue);
    }
}