package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ApplicationNamespace, String> {

    public ApplicationNamespaceXmlSerializer() {
        super(ApplicationNamespace::getValue);
    }
}