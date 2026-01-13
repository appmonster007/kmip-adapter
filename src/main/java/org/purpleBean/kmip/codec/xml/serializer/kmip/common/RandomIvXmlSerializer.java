package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RandomIv, Boolean> {

    public RandomIvXmlSerializer() {
        super(RandomIv::getValue);
    }
}