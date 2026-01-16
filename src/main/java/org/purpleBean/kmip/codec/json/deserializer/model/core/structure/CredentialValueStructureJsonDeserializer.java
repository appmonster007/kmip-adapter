package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CredentialValueStructure;

import java.io.IOException;

public class CredentialValueStructureJsonDeserializer extends AbstractKmipStructureJsonDeserializer<CredentialValueStructure, CredentialValueStructure.CredentialValueStructureBuilder> {

    public CredentialValueStructureJsonDeserializer() {
        super(CredentialValueStructure.kmipTag, CredentialValueStructure.encodingType);
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