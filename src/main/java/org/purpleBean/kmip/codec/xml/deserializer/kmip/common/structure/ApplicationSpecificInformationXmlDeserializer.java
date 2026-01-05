package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ApplicationData;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.common.structure.ApplicationSpecificInformation;

import java.io.IOException;
import java.util.Map;

public class ApplicationSpecificInformationXmlDeserializer extends KmipDataTypeXmlDeserializer<ApplicationSpecificInformation> {
    private final KmipTag kmipTag = ApplicationSpecificInformation.kmipTag;
    private final EncodingType encodingType = ApplicationSpecificInformation.encodingType;

    @Override
    public ApplicationSpecificInformation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class, "Expected XML object for ApplicationSpecificInformation");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class, "Invalid Tag for ApplicationSpecificInformation");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder = ApplicationSpecificInformation.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        ApplicationSpecificInformation applicationspecificinformation = builder.build();

        if (!applicationspecificinformation.isSupported()) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class, "ApplicationSpecificInformation not supported for spec " + spec);
            return null;
        }

        return applicationspecificinformation;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the XML node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(
            ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.APPLICATION_NAMESPACE ->
                    builder.applicationNamespace(p.getCodec().treeToValue(node, ApplicationNamespace.class));
            case KmipTag.Standard.APPLICATION_DATA ->
                    builder.applicationData(p.getCodec().treeToValue(node, ApplicationData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}