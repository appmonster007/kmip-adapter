package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ApplicationNamespace, String> {

    public ApplicationNamespaceXmlDeserializer() {
        super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType, String.class, value -> ApplicationNamespace.builder().value(value).build());
    }
}