package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CredentialValueGenericStructure;

import java.io.IOException;

public class CredentialValueGenericStructureXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CredentialValueGenericStructure, CredentialValueGenericStructure.CredentialValueGenericStructureBuilder> {

    public CredentialValueGenericStructureXmlDeserializer() {
        super(CredentialValueGenericStructure.kmipTag, CredentialValueGenericStructure.encodingType);
    }

    @Override
    protected CredentialValueGenericStructure.CredentialValueGenericStructureBuilder createBuilder() {
        return CredentialValueGenericStructure.builder();
    }

    @Override
    protected void setValue(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);

        builder.value(ctxt.readValue(p, KmipDataType.class));
    }

    @Override
    protected CredentialValueGenericStructure build(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder) {
        return builder.build();
    }
}