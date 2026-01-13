package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SplitKeyMethod, String> {

    public SplitKeyMethodXmlSerializer() {
        super(SplitKeyMethod::getDescription);
    }
}