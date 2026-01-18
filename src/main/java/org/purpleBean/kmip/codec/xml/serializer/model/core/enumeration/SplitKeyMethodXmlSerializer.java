package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;

public class SplitKeyMethodXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SplitKeyMethod, String> {

    public SplitKeyMethodXmlSerializer() {
        super(SplitKeyMethod::getDescription);
    }
}