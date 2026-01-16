package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CredentialValueGenericStructure;

import java.io.IOException;

public class CredentialValueGenericStructureXmlDeserializer extends AbstractKmipStructureXmlDeserializer<CredentialValueGenericStructure, CredentialValueGenericStructure.CredentialValueGenericStructureBuilder> {

    public CredentialValueGenericStructureXmlDeserializer() {
        super(CredentialValueGenericStructure.kmipTag);
    }

    @Override
    protected CredentialValueGenericStructure.CredentialValueGenericStructureBuilder createBuilder() {
        return CredentialValueGenericStructure.builder();
    }

    @Override
    protected void setValue(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, KmipDataType.class));
    }

    @Override
    protected CredentialValueGenericStructure build(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder) {
        return builder.build();
    }
}