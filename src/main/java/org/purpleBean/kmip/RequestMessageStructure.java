package org.purpleBean.kmip;

import java.util.List;

public interface RequestMessageStructure extends KmipStructure {
    RequestHeaderStructure getRequestHeader();

    List<? extends RequestBatchItemStructure> getRequestBatchItems();

    List<? extends Exception> getRequestBatchItemErrors();
}
