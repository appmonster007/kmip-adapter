package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ApplicationNamespaceXmlSerializer extends AbstractKmipXmlSerializer<ApplicationNamespace, String> {

    public ApplicationNamespaceXmlSerializer() {
        super(ApplicationNamespace::getValue);
    }
}