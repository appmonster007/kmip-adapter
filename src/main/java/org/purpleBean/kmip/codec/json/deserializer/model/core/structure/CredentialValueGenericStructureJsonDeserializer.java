package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CredentialValueGenericStructure;

import java.io.IOException;

public class CredentialValueGenericStructureJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CredentialValueGenericStructure, CredentialValueGenericStructure.CredentialValueGenericStructureBuilder> {

    public CredentialValueGenericStructureJsonDeserializer() {
        super(CredentialValueGenericStructure.kmipTag, CredentialValueGenericStructure.encodingType);
    }

    @Override
    protected CredentialValueGenericStructure.CredentialValueGenericStructureBuilder createBuilder() {
        return CredentialValueGenericStructure.builder();
    }

    @Override
    protected void setValue(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, KmipDataType.class));
    }

    @Override
    protected CredentialValueGenericStructure build(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder) {
        return builder.build();
    }
}
