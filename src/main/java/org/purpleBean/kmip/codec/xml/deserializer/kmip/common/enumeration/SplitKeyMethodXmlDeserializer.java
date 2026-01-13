package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SplitKeyMethod, String> {

    public SplitKeyMethodXmlDeserializer() {
        super(SplitKeyMethod.kmipTag, SplitKeyMethod.encodingType, String.class, value -> new SplitKeyMethod(SplitKeyMethod.fromName(value)));
    }
}