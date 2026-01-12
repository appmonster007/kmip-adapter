package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.ApplicationData;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.common.structure.ApplicationSpecificInformation;

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
            case KmipTag.Standard.APPLICATION_NAMESPACE -> builder.applicationNamespace(ctxt.readValue(p, ApplicationNamespace.class));
            case KmipTag.Standard.APPLICATION_DATA -> builder.applicationData(ctxt.readValue(p, ApplicationData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ApplicationSpecificInformation build(ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder) {
        return builder.build();
    }
}