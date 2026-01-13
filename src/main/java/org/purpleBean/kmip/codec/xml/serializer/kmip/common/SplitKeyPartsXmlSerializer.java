package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.SplitKeyParts;

public class SplitKeyPartsXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SplitKeyParts, Integer> {

    public SplitKeyPartsXmlSerializer() {
        super(SplitKeyParts::getValue);
    }
}