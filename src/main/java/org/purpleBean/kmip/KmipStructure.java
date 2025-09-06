package org.purpleBean.kmip;

import java.util.List;

public interface KmipStructure extends KmipDataType {
    List<KmipDataType> getValues();
}
