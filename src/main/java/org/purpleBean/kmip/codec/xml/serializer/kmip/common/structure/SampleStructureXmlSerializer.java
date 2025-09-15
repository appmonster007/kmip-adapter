package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.structure.SampleStructure;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SampleStructureXmlSerializer extends JsonSerializer<SampleStructure> {

    @Override
    public void serialize(SampleStructure sampleStructure, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!sampleStructure.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("SampleStructure not supported for spec " + spec);
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = sampleStructure.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(sampleStructure);

        List<KmipDataType> values = sampleStructure.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null) {
                serializers.defaultSerializeField(
                        kmipDataType.getKmipTag().getDescription(),
                        kmipDataType,
                        gen
                );
            }
        }

        xmlGen.writeEndObject();
    }
}
