package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ApplicationData;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;
import org.purpleBean.kmip.model.core.structure.ApplicationSpecificInformation;

import java.io.IOException;

public class ApplicationSpecificInformationXmlDeserializer extends AbstractKmipStructureXmlDeserializer<ApplicationSpecificInformation, ApplicationSpecificInformation.ApplicationSpecificInformationBuilder> {

    public ApplicationSpecificInformationXmlDeserializer() {
        super(ApplicationSpecificInformation.kmipTag);
    }

    @Override
    protected ApplicationSpecificInformation.ApplicationSpecificInformationBuilder createBuilder() {
        return ApplicationSpecificInformation.builder();
    }

    @Override
    protected void setValue(ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.APPLICATION_NAMESPACE ->
                    builder.applicationNamespace(ctxt.readValue(p, ApplicationNamespace.class));
            case KmipTag.Standard.APPLICATION_DATA -> builder.applicationData(ctxt.readValue(p, ApplicationData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ApplicationSpecificInformation build(ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder) {
        return builder.build();
    }
}