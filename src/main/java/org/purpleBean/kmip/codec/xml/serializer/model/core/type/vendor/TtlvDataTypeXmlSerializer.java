package org.purpleBean.kmip.codec.xml.serializer.model.core.type.vendor;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;

public class TtlvDataTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<TtlvDataType, Object> {

    public TtlvDataTypeXmlSerializer() {
        super(TtlvDataType::getValue);
    }

}
