package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ExtensionName, String> {

    public ExtensionNameXmlSerializer() {
        super(ExtensionName::getValue);
    }
}