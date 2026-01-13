package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ExtensionType;

public class ExtensionTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ExtensionType, Integer> {

    public ExtensionTypeXmlSerializer() {
        super(ExtensionType::getValue);
    }
}