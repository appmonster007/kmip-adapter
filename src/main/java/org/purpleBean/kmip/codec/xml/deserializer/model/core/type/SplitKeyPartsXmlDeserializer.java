package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;

public class SplitKeyPartsXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SplitKeyParts, Integer> {

    public SplitKeyPartsXmlDeserializer() {
        super(SplitKeyParts.kmipTag, SplitKeyParts.encodingType, Integer.class, value -> SplitKeyParts.builder().value(value).build());
    }
}