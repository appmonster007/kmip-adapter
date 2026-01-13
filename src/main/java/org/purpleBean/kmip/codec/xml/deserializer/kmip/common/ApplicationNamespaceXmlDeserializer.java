package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ApplicationNamespace, String> {

    public ApplicationNamespaceXmlDeserializer() {
        super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType, String.class, value -> ApplicationNamespace.builder().value(value).build());
    }
}