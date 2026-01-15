package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ExtensionName;

public class ExtensionNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ExtensionName, String> {

    public ExtensionNameXmlSerializer() {
        super(ExtensionName::getValue);
    }
}