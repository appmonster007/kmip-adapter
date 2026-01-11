package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceXmlDeserializer extends AbstractKmipXmlDeserializer<ApplicationNamespace, String> {

    public ApplicationNamespaceXmlDeserializer() {
        super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType, String.class, value -> ApplicationNamespace.builder().value(value).build());
    }
}