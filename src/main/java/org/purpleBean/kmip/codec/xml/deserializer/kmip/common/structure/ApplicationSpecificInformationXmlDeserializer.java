package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ApplicationData;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.common.structure.ApplicationSpecificInformation;

import java.io.IOException;

public class ApplicationSpecificInformationXmlDeserializer extends KmipDataTypeXmlDeserializer<ApplicationSpecificInformation> {
    private final KmipTag kmipTag = ApplicationSpecificInformation.kmipTag;

    @Override
    public ApplicationSpecificInformation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class, "Invalid Tag for ApplicationSpecificInformation");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder = ApplicationSpecificInformation.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(ApplicationSpecificInformation.class, "Unexpected token: " + p.currentToken());
            }
        }

        ApplicationSpecificInformation applicationspecificinformation = builder.build();

        if (!applicationspecificinformation.isSupported()) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class, "ApplicationSpecificInformation not supported for spec " + spec);
            return null;
        }

        return applicationspecificinformation;
    }

    private void setValue(
            ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.APPLICATION_NAMESPACE ->
                    builder.applicationNamespace(ctxt.readValue(p, ApplicationNamespace.class));
            case KmipTag.Standard.APPLICATION_DATA -> builder.applicationData(ctxt.readValue(p, ApplicationData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}