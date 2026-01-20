package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;

public class SplitKeyMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SplitKeyMethod, String> {

    public SplitKeyMethodXmlDeserializer() {
        super(SplitKeyMethod.kmipTag, SplitKeyMethod.encodingType, String.class, value -> SplitKeyMethod.fromName(value).inst());
    }
}