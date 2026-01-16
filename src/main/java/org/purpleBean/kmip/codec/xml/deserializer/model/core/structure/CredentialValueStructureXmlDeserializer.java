package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CredentialValueStructure;

import java.io.IOException;

public class CredentialValueStructureXmlDeserializer extends AbstractKmipStructureXmlDeserializer<CredentialValueStructure, CredentialValueStructure.CredentialValueStructureBuilder> {

    public CredentialValueStructureXmlDeserializer() {
        super(CredentialValueStructure.kmipTag);
    }

    @Override
    protected CredentialValueStructure.CredentialValueStructureBuilder createBuilder() {
        return CredentialValueStructure.builder();
    }

    @Override
    protected void setValue(CredentialValueStructure.CredentialValueStructureBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, KmipDataType.class));
    }

    @Override
    protected CredentialValueStructure build(CredentialValueStructure.CredentialValueStructureBuilder builder) {
        return builder.build();
    }
}